import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationWithdrawRequest {
  //  @NotNull
    private Long transactionId;

//    @NotNull
//    @Size(min = 6, max = 6)
    private String validationCode ;// = UUID.randomUUID().toString().substring(0, 6);
//    public ValidationWithdrawRequest() {
//    }
//    public ValidationWithdrawRequest(Long transactionId, String validationCode) {
//        this.transactionId = transactionId;
//        this.validationCode = validationCode;
//    }
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
