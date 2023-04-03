package FinanceMe.PiDev.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationRequest {
//    @NotNull(message = "Transaction ID cannot be null")
 //   private Long transactionId;

    private String validationCode ;
//    @NotBlank(message = "Validation code cannot be blank")
//    @Size(min = 6, max = 6, message = "Validation code must be 6 characters long")
//    public ValidationRequest() {
//    }
//
//    public ValidationRequest(Long transactionId, String validationCode) {
//        this.transactionId = transactionId;
//        this.validationCode = validationCode;
//    }
//
//    public Long getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(Long transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public String getValidationCode() {
//        return validationCode;
//    }
//
//    public void setValidationCode(String validationCode) {
//        this.validationCode = validationCode;
//    }
}
