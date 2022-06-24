package ir.dotin.spring.controller;

import ir.dotin.spring.dto.PrintRequestRequest;
import ir.dotin.spring.dto.PrintRequestResponse;
import ir.dotin.spring.exception.BusinessException;
import ir.dotin.spring.service.PrintRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class PrintRequestRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintRequestRestController.class);

    private final PrintRequestService printRequestService;

    public PrintRequestRestController(PrintRequestService printRequestService) {
        this.printRequestService = printRequestService;
    }

    @GetMapping("/api/PrintRequest/getAll/{pageNo}/{pageSize}")
    public List<PrintRequestResponse> getAll(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestHeader Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet())
            LOGGER.info((entry.getKey() + "    " + entry.getValue()));

        return printRequestService.getAllPrintRequest(pageNo, pageSize);
    }

    @GetMapping("/api/PrintRequest/getByCode/{code}")
    public PrintRequestResponse getByCode(@PathVariable @Valid Long code) {
        try {
            return printRequestService.getByPrintRequestCode(code);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("/api/PrintRequest/create")
    public PrintRequestResponse create(@Valid @RequestBody PrintRequestRequest printRequestReq) {
        try {
            return printRequestService.insertPrintRequest(printRequestReq);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @PatchMapping("/api/PrintRequest/updateCardPanByCode/{code}/{cardPan}")
    public void updateCardPanByCode(@PathVariable @Valid String code, @PathVariable @Valid String cardPan) {
        try {
            printRequestService.updateCardPanByCode(code, cardPan);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @PutMapping("/api/PrintRequest/update/{code}")
    public PrintRequestResponse update(@PathVariable @Valid Long code, @RequestBody @Valid PrintRequestRequest printRequestReq) {
        try {
            return printRequestService.updatePrintRequest(code, printRequestReq);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/api/PrintRequest/delete/{code}")
    public void delete(@PathVariable @Valid Long code) {
        printRequestService.deletePrintRequest(code);
    }

    @GetMapping("/api/PrintRequest/getAllIpAddressByBranchCode/{branchCode}")
    public List<String> getAllIpAddressByBranchCode(@PathVariable @Valid String branchCode) {
        return printRequestService.getAllIpAddressByBranchCode(branchCode);
    }
}

