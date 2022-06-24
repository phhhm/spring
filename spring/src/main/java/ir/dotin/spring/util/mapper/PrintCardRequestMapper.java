package ir.dotin.spring.util.mapper;


import ir.dotin.spring.dto.PrintRequestRequest;
import ir.dotin.spring.dto.PrintRequestResponse;
import ir.dotin.spring.model.PrintRequest;
import ir.dotin.spring.model.PrintRequestEmbeddedId;

import java.util.Date;

public class PrintCardRequestMapper {

    public static PrintRequestResponse mapPrintRequestToPrintRequestRes(PrintRequest printRequest) {
        return new PrintRequestResponse()
                .setIpAddress(printRequest.getIpAddress())
                .setCardPAN(printRequest.getCardPAN())
                .setBranchCode(printRequest.getBranchCode())
                .setIssueDate(printRequest.getIssueDate())
                .setPersonalCode(printRequest.getPersonalCode())
                .setCode(printRequest.getCode());
    }

    public static PrintRequest mapPrintRequestReqToPrintRequest(PrintRequestRequest printRequestReq) {
        return new PrintRequest()
                .setEmbeddedId(new PrintRequestEmbeddedId(printRequestReq.getBranchCode(), printRequestReq.getIpAddress()))
                .setIpAddress(printRequestReq.getIpAddress())
                .setCardPAN(printRequestReq.getCardPAN())
                .setBranchCode(printRequestReq.getBranchCode())
                .setIssueDate(new Date())
                .setPersonalCode(printRequestReq.getPersonalCode());
    }
}
