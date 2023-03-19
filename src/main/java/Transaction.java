import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "Transaction")
public class Transaction implements Serializable {

    @Id
    private int transaction_id;
    private int account_id;
    private String transaction_type;
    private double amount;
    private String source;
    private String status;
    private String reason_code;
    private LocalDateTime created_at;

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", account_id=" + account_id +
                ", transaction_type='" + transaction_type + '\'' +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                ", status='" + status + '\'' +
                ", reason_code='" + reason_code + '\'' +
                ", created_at=" + created_at +
                '}';
    }

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long id_transaction;

  //  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy")
 // @Temporal(TemporalType.TIMESTAMP)
 // @Column(name = "transaction_date", nullable = false, updatable = false)
 // @GeneratedValue(strategy = GenerationType.AUTO)
  private Date transaction_date;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;


    @Override
    public String toString() {
        return "Transaction{" +
                "id_transaction=" + id_transaction +
                ", date_transaction=" + transaction_date +
                ", amount=" + amount +
                ", senderAccount=" + senderAccount +
                ", receiverAccount=" + receiverAccount +
                '}';
    }
    */

}
