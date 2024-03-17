import { Component, OnInit, ViewChild } from '@angular/core';
import { TransactionsService } from '../transactions.service';
import { NgForm } from '@angular/forms';
import { NotificationServiceService } from '../notification-service.service';

@Component({
  selector: 'app-withdraw-form',
  templateUrl: './withdraw-form.component.html',
  styleUrls: ['./withdraw-form.component.css']
})
export class WithdrawFormComponent implements OnInit {
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

    this.transactionsService.withdraw(this.compteDestinataire, this.montant)
      .subscribe(
        data => {
          console.log(data);
          this.notificationService.showSuccess('Withdraw success! Please check your email and validate deposit below.');
          this.showSuccessMessage = true;
          this.depositForm.resetForm(); 
        },
        error => {
          console.error(error);
         // console.log(error.error);
         // this.notificationService.showError('Withdraw failed! Please try again later.');
        }
      );
  }



}
