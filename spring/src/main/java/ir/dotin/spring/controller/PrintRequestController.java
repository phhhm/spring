package ir.dotin.spring.controller;

import ir.dotin.spring.dto.PrintRequestRequest;
import ir.dotin.spring.dto.PrintRequestResponse;
import ir.dotin.spring.service.PrintRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/printRequest")
public class PrintRequestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintRequestController.class);

    private final PrintRequestService printRequestService;

    public PrintRequestController(PrintRequestService printRequestService) {
        this.printRequestService = printRequestService;
    }

    @GetMapping("/getForm")
    public String addBookView(Model model) {
        model.addAttribute("printRequestReq", new PrintRequestRequest());
        return "create";
    }

    @RequestMapping("/saveDetails")
    public String saveDetails(@Valid @ModelAttribute PrintRequestRequest printRequestReq, BindingResult bindingResult, Model model) {
        String destPage = "";
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("printRequestReq", new PrintRequestRequest());
                List<ObjectError> objectErrors = bindingResult.getAllErrors();
                bindingResult.getModel().put("errors", objectErrors.get(0));
                model.addAttribute("hasError", true);
                model.addAttribute("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
                destPage = "create";
            } else {
                PrintRequestResponse printRequestRes = printRequestService.insertPrintRequest(printRequestReq);
                model.addAttribute("printRequestRes", printRequestRes);
                destPage = "result";
            }
            return destPage;
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        }
    }
}
