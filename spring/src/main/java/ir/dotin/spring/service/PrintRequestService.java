package ir.dotin.spring.service;

import ir.dotin.spring.dto.PrintRequestRequest;
import ir.dotin.spring.dto.PrintRequestResponse;
import ir.dotin.spring.exception.BusinessException;
import ir.dotin.spring.model.PrintRequest;
import ir.dotin.spring.repository.PrintRequestRepo;
import ir.dotin.spring.repository.custom.PrintRequestCustomRepImpl;
import ir.dotin.spring.util.Application;
import ir.dotin.spring.util.ExecuteTime;
import ir.dotin.spring.util.mapper.PrintCardRequestMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PrintRequestService implements Application {
    private final PrintRequestRepo printRequestRepo;
    private final PrintRequestCustomRepImpl printRequestCustomRep;
    private ApplicationContext applicationContext;

    public PrintRequestService(PrintRequestRepo printRequestRepo, PrintRequestCustomRepImpl printRequestCustomRep) {
        this.printRequestRepo = printRequestRepo;
        this.printRequestCustomRep = printRequestCustomRep;
    }

    @ExecuteTime
    @Transactional(readOnly = true)
    public List<PrintRequestResponse> getAllPrintRequest(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("code"));
        Page<PrintRequest> printRequests = printRequestRepo.findAll(paging);
        return printRequests.stream().map(PrintCardRequestMapper::mapPrintRequestToPrintRequestRes).collect(Collectors.toList());
    }

    @Transactional
    @ExecuteTime
    public PrintRequestResponse insertPrintRequest(PrintRequestRequest printRequestReq) throws BusinessException {
        boolean isPrintRequestExists = printRequestRepo.existsPrintRequestByBranchCodeAndIpAddress(printRequestReq.getBranchCode(), printRequestReq.getIpAddress());

        if (isPrintRequestExists)
            throw new BusinessException();

        if (printRequestReq.getIssueDate() == null)
            throw new BusinessException();

        PrintRequest newPrintRequest = PrintCardRequestMapper.mapPrintRequestReqToPrintRequest(printRequestReq);
        newPrintRequest.setCode(new Random().nextLong());
        newPrintRequest = printRequestRepo.save(newPrintRequest);
        return PrintCardRequestMapper.mapPrintRequestToPrintRequestRes(newPrintRequest);
    }

    @Transactional
    @ExecuteTime
    public void updateCardPanByCode(String code, String cardPan) throws BusinessException {
        boolean isExistPrintRequest = printRequestRepo.existsByCode(code);

        if (isExistPrintRequest)
            throw new BusinessException();

        printRequestRepo.updateCardPanByBranchCode(code, cardPan);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @ExecuteTime
    public PrintRequestResponse updatePrintRequest(Long code, PrintRequestRequest printRequestReq) throws BusinessException {
        Optional<PrintRequest> optionalPrintRequest = printRequestRepo.findByCode(code);

        if (optionalPrintRequest.isEmpty())
            throw new BusinessException();

        PrintRequest oldPrintRequest = adaptPrintRequestResToPrintRequest(printRequestReq, optionalPrintRequest.get());
        return PrintCardRequestMapper.mapPrintRequestToPrintRequestRes(printRequestRepo.save(oldPrintRequest));
    }

    @ExecuteTime
    public PrintRequestResponse getByPrintRequestCode(Long code) throws BusinessException {
        Optional<PrintRequest> optionalPrintRequest = printRequestRepo.findByCode(code);

        if (optionalPrintRequest.isEmpty())
            throw new BusinessException();

        return PrintCardRequestMapper.mapPrintRequestToPrintRequestRes(optionalPrintRequest.get());
    }


    @Transactional
    @ExecuteTime
    public void deletePrintRequest(Long code) {
        Optional<PrintRequest> optionalPrintRequest = printRequestRepo.findByCode(code);
        if (optionalPrintRequest.isEmpty()) throw new IllegalArgumentException();
        printRequestRepo.delete(optionalPrintRequest.get());
    }

    public List<String> getAllIpAddressByBranchCode(String branchCode) {
        return printRequestCustomRep.getAllIpAddressByBranchCode(branchCode);
    }

    private PrintRequest adaptPrintRequestResToPrintRequest(PrintRequestRequest printRequestReq, PrintRequest oldPrintRequest) {
        oldPrintRequest.setPersonalCode(printRequestReq.getPersonalCode()).setBranchCode(printRequestReq.getBranchCode()).setCardPAN(printRequestReq.getCardPAN()).setIpAddress(printRequestReq.getIpAddress()).setIssueDate(new Date());
        return oldPrintRequest;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}