package FinanceMe.PiDev.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class DepositRequest {
    private Long compteDestinataire;
    private float montant;
    private String typeTransaction;
    private LocalDateTime tempsValidation;

}

//    public DepositRequest() {
//    }
//
//    public DepositRequest(Long compteDestinataire, float montant, String typeTransaction) {
//        this.compteDestinataire = compteDestinataire;
//        this.montant = montant;
//        this.typeTransaction = typeTransaction;
//    }
//
//    public Long getCompteDestinataire() {
//        return compteDestinataire;
//    }
//
//    public void setCompteDestinataire(Long compteDestinataire) {
//        this.compteDestinataire = compteDestinataire;
//    }
//
//    public float getMontant() {
//        return montant;
//    }
//
//    public void setMontant(float montant) {
//        this.montant = montant;
//    }
//
//    public String getTypeTransaction() {
//        return typeTransaction;
//    }
//
//    public void setTypeTransaction(String typeTransaction) {
//        this.typeTransaction = typeTransaction;
//    }