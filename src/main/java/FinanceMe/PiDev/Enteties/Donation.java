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
@Table( name = "Offer")
public class Donation implements Serializable {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donation" , updatable = false)
    private Long id_donation;

    private float amount;

    private String payment_method;
    private String status;

    @Override
    public String toString() {
        return "Donation{" +
                "id_donation=" + id_donation +
                ", amount=" + amount +
                ", payment_method='" + payment_method + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}