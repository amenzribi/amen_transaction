export interface Transaction {
    id: number;
    montant: number;
    type_transaction : String
    validationCode : String
    etat : String
    compteEmetteur: number
    compteDestinataire: number
    feeTransaction : number
    date: Date;
  }