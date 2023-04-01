package FinanceMe.PiDev.DTO;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class WithdrawRequest {
    private Long compteDestinataire;
//    @NotNull
//    @Positive
    private float montant;
    private String typeTransaction;


//    public WithdrawRequest() {
//    }
//
//    public WithdrawRequest(Long compteDestinataire, float montant, String typeTransaction) {
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
}
