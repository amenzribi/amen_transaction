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
@Table( name = "Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAccount")
    private Integer IdAccount;
    private Long Balance;
    private Date DateOpened;

    @Override
    public String toString() {
        return "Account{" +
                "IdAccount=" + IdAccount +
                ", Balance=" + Balance +
                ", DateOpened=" + DateOpened +
                '}';
    }
}

