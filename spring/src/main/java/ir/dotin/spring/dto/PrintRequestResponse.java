package ir.dotin.spring.dto;

import java.util.Date;
import java.util.Objects;

public class PrintRequestResponse {
    private String personalCode;
    private String branchCode;
    private String cardPAN;
    private String ipAddress;
    private Date issueDate;
    private Long code;



    @Override
    public int hashCode() {
        return Objects.hash(personalCode, branchCode, cardPAN, ipAddress, issueDate, code);
    }


    public String getPersonalCode() {
        return personalCode;
    }

    public PrintRequestResponse setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public PrintRequestResponse setBranchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public PrintRequestResponse setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public PrintRequestResponse setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public PrintRequestResponse setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public Long getCode() {
        return code;
    }

    public PrintRequestResponse setCode(Long code) {
        this.code = code;
        return this;
    }
}
