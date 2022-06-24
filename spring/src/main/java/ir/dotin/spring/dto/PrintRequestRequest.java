package ir.dotin.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class PrintRequestRequest {

    @Size(min = 5 ,max = 10,message ="{PrintRequestRequest.personalCode_invalid}")
    @NotBlank(message = "{PrintRequestRequest.personalCode_required}")
    private String personalCode;

    @Size(min = 3,message = "{PrintRequestRequest.branchCode_invalid}")
    @NotBlank(message = "{PrintRequestRequest.branchCode_required}")
    private String branchCode;

    @Size(min = 16,max = 16,message ="{PrintRequestRequest.cardPAN_invalid}" )
    @NotBlank(message = "{PrintRequestRequest.cardPAN_required}")
    private String cardPAN;

    @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$",message = "{PrintRequestRequest.ipAddress_invalid}")
    @NotBlank(message = "{PrintRequestRequest.ipAddress_required}")
    private String ipAddress;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "{PrintRequestRequest.issueDate_required}")
    private Date issueDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintRequestRequest that = (PrintRequestRequest) o;
        return personalCode.equals(that.personalCode) &&
                branchCode.equals(that.branchCode) &&
                cardPAN.equals(that.cardPAN) &&
                ipAddress.equals(that.ipAddress) &&
                issueDate.equals(that.issueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalCode, branchCode, cardPAN, ipAddress, issueDate);
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public PrintRequestRequest setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public PrintRequestRequest setBranchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public PrintRequestRequest setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public PrintRequestRequest setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public PrintRequestRequest setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        return this;
    }
}
