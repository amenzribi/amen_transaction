package FinanceMe.PiDev.Enteties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long compte_id;
    private String nom;

    private float solde;
    private String Email;

    //@OneToMany
    @OneToMany(mappedBy = "compteEmetteur")
    Set<Transaction> transactionSet;

    @OneToMany(mappedBy = "compteDestinataire")
    Set<Transaction> transactionS;

    @Override
    public String toString() {
        return "Compte{" +
                "compte_id=" + compte_id +
                ", nom='" + nom + '\'' +
                ", solde=" + solde +
                ", Email='" + Email + '\'' +
                ", transactionSet=" + transactionSet +
                ", transactionS=" + transactionS +
                '}';
    }
}

