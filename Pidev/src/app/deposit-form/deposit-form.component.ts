import { Component, OnInit, ViewChild } from '@angular/core';
import { TransactionsService } from '../transactions.service';
import { Transaction } from '../Model/transaction';
import { transactionComponent } from '../transactions/transaction.component';
import { FormsModule, NgForm } from '@angular/forms';
import { NotificationServiceService } from '../notification-service.service';


@Component({
  selector: 'app-deposit-form',
 
  templateUrl: './deposit-form.component.html',
  styleUrls: ['./deposit-form.component.css']
})
export class DepositFormComponent implements OnInit {

  @ViewChild('depositForm') depositForm: NgForm;

  compteDestinataire: number;
  montant: number;
  compteDestinataireError: boolean;
  montantError: boolean;
  showSuccessMessage: boolean;
  
  
  constructor(private transactionsService: TransactionsService, private notificationService: NotificationServiceService) {
    this.compteDestinataireError = false;
    this.montantError = false;
    this.showSuccessMessage = false;

  }

  ngOnInit(): void {
  }
 
  
  //onSubmit() {
    //this.transactionsService.deposit(this.compteDestinataire, this.montant).subscribe(
      //(data) => {
        //console.log(data);
        //this.notificationService.showSuccess('Dépôt effectué avec succès', 'Succès');
      //},
      //(error) => {
        //console.error(error);
        //this.notificationService.showError('Une erreur est survenue lors du dépôt', 'Erreur');
      //}
    //);
  //}

  




  onSubmit() {
    this.compteDestinataireError = false;
   this.montantError = false;
    this.showSuccessMessage = false;

    if (this.compteDestinataire < 0 || isNaN(this.compteDestinataire)) {
      this.compteDestinataireError = true;
      return;
    }

    if (this.montant < 0 || isNaN(this.montant)) {
     this.montantError = true;
      return;
    }

    this.transactionsService.deposit(this.compteDestinataire, this.montant)
      .subscribe(
       data => {
          console.log(data);
          this.notificationService.showSuccess('Deposit success! Please check your email and validate deposit below.');
         this.showSuccessMessage = true;
          this.depositForm.reset();
        },
       error => {
          console.error(error);
          console.log(error.error);
          this.showSuccessMessage = false;
      //   this.notificationService.showError('Deposit failed! Please try again later.');
        }
     );
  }

  }