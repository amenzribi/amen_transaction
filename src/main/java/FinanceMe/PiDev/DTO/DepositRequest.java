package FinanceMe.PiDev.DTO;

public  class DepositRequest {
    private Long compteDestinataire;
    private float montant;
    private String typeTransaction;

    public DepositRequest() {
    }

    public DepositRequest(Long compteDestinataire, float montant, String typeTransaction) {
        this.compteDestinataire = compteDestinataire;
        this.montant = montant;
        this.typeTransaction = typeTransaction;
    }

    public Long getCompteDestinataire() {
        return compteDestinataire;
    }

    public void setCompteDestinataire(Long compteDestinataire) {
        this.compteDestinataire = compteDestinataire;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
}
