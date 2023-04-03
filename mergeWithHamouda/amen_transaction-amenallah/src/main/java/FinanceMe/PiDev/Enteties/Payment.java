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
@Table( name = "Payment")
public class

Payment implements Serializable {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment" , updatable = false)
    private Long id_payment;

    private float amount;
    private Date payment_date;
    private String status;
    private String devise;
    private String mode;

    @Override
    public String toString() {
        return "Payment{" +
                "id_payment=" + id_payment +
                ", amount=" + amount +
                ", payment_date=" + payment_date +
                ", status='" + status + '\'' +
                ", devise='" + devise + '\'' +
                ", mode='" + mode + '\'' +
                '}';
    }
}
