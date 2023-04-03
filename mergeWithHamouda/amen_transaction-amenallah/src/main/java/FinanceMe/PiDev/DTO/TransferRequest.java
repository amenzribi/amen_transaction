package FinanceMe.PiDev.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private Long compteEmetteur;
    private Long compteDestinataire;
    private float montant;
    private String type_transaction;
}
