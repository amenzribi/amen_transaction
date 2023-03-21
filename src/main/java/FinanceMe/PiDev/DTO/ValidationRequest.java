package FinanceMe.PiDev.DTO;

import java.util.UUID;

public class ValidationRequest {
    private Long transactionId;
    private String validationCode = UUID.randomUUID().toString().substring(0, 6);

    public ValidationRequest() {
    }

    public ValidationRequest(Long transactionId, String validationCode) {
        this.transactionId = transactionId;
        this.validationCode = validationCode;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
