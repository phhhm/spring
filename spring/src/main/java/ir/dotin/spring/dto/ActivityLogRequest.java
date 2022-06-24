package ir.dotin.spring.dto;

public class ActivityLogRequest {
    private String name;
    private String personalCode;
    private String cardPAN;
    private String applicationType;

    public String getName() {
        return name;
    }

    public ActivityLogRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public ActivityLogRequest setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public ActivityLogRequest setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
        return this;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public ActivityLogRequest setApplicationType(String applicationType) {
        this.applicationType = applicationType;
        return this;
    }
}
