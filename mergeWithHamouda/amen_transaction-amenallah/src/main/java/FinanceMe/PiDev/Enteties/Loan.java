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

public class Loan implements Serializable {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan" )
    private Integer id_loan;

    private float amount;
    private Date credit_start_date;
    private Date credit_end_date;
    private String status;
    private Date due_date;
    private String interest_rate;

    @Override
    public String toString() {
        return "Loan{" +
                "id_loan=" + id_loan +
                ", amount=" + amount +
                ", credit_start_date=" + credit_start_date +
                ", credit_end_date=" + credit_end_date +
                ", status='" + status + '\'' +
                ", due_date=" + due_date +
                ", interest_rate='" + interest_rate + '\'' +
                '}';
    }

}

