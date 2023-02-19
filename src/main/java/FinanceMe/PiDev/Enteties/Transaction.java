package FinanceMe.PiDev.Enteties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "Transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTransaction")
    private Integer IdTransaction;
    private Float Amount;
    private Date DateTransaction;

    @Override
    public String toString() {
        return "Transaction{" +
                "IdTransaction=" + IdTransaction +
                ", Amount=" + Amount +
                ", DateTransaction=" + DateTransaction +
                ", Devise='" + Devise + '\'' +
                ", Type='" + Type + '\'' +
                ", Status='" + Status + '\'' +
                ", PaymentMethod='" + PaymentMethod + '\'' +
                ", Mode='" + Mode + '\'' +
                '}';
    }

    private String Devise;
    private String Type;
    private String Status;
    private String PaymentMethod;
    private String Mode;
}
