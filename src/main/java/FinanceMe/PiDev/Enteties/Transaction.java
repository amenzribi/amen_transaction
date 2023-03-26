package FinanceMe.PiDev.Enteties;

import com.zaxxer.hikari.util.FastList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private float montant;

    private LocalDateTime date;
    private String type_transaction ;
    //@Column(name = "validation_code")
    private String validationCode;
    private String etat;
    //private LocalDateTime TempsValidation ;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "etat_transaction")
//    private EtatTransaction etatTransaction;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compteSrcId" )
    private Compte compte;

 */
    @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "idcompte_s")
    private Compte compteEmetteur;

    @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "idcompte_r")
    private Compte compteDestinataire;
/*
@ManyToOne(fetch = FetchType.LAZY)

@JoinColumn(name = "compteSrcId" )
  private Compte  compteSrc;

 */

/*
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "compteDestId")
private Compte compteDest;

 */

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                ", type_transaction='" + type_transaction + '\'' +
                ", validationCode='" + validationCode + '\'' +
                ", compteEmetteur=" + compteEmetteur +
                ", compteDestinataire=" + compteDestinataire +
                '}';
    }

    /*

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                ", compte=" + compte +
                '}';
    }

 */
}
