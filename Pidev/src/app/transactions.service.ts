import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  readonly API_URL = 'http://localhost:8080/transactions';

  constructor(private http: HttpClient) { }

  getAllTransactions() {
    return this.http.get(this.API_URL);
  }

  deposit(compteDestinataire: number, montant: number) {
    return this.http.post<any>(`${this.API_URL}/deposit`, {compteDestinataire, montant});
  }
  withdraw(compteDestinataire: number, montant: number) {
    return this.http.post<any>(`${this.API_URL}/withdraw`, {compteDestinataire, montant});
  }


  validateTransaction(validationCode: string) {
    return this.http.post(`${this.API_URL}/validate`, { validationCode });
  }

  transfer(compteDestinataire: number , compteEmetteur: number , montant: number){
    return this.http.post(`${this.API_URL}/transfert`, { compteDestinataire, compteEmetteur, montant });
  }
}
