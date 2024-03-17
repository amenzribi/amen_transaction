import { Component, OnInit, ViewChild } from '@angular/core';
import { TransactionsService } from '../transactions.service';
import { NgForm } from '@angular/forms';
import { NotificationServiceService } from '../notification-service.service';

@Component({
  selector: 'app-transfer-form',
  templateUrl: './transfer-form.component.html',
  styleUrls: ['./transfer-form.component.css']
})
export class TransferFormComponent implements OnInit {
  @ViewChild('transferForm') transferForm: NgForm;
  compteDestinataireError: boolean;
  compteEmetteurError: boolean;
  montantError: boolean;
  showSuccessMessage: boolean;
  

  compteDestinataire: number;
  compteEmetteur : number;
  montant: number;
  constructor(private transactionsService : TransactionsService , private notificationService: NotificationServiceService) {
    this.compteDestinataireError = false;
    this.compteEmetteurError = false;
    this.montantError = false;
    this.showSuccessMessage = false;

   }

  ngOnInit(): void {
  }
  onSubmit() {
    this.compteDestinataireError = false;
this.compteEmetteurError = false;
this.montantError = false;
this.showSuccessMessage = false;
if (this.compteDestinataire < 0 || isNaN(this.compteDestinataire)) {
  this.compteDestinataireError = true;
  return;
}

if (this.compteEmetteur < 0 || isNaN(this.compteEmetteur)) {
  this.compteEmetteurError = true;
  return;
}

if (this.montant < 0 || isNaN(this.montant)) {
  this.montantError = true;
  return;
}


this.transactionsService.transfer(this.compteDestinataire,this.compteEmetteur ,this.montant)
.subscribe(
  data => {
    console.log(data);
    this.notificationService.showSuccess('Transfer success! Please check your email and validate deposit below.');
    this.showSuccessMessage = true;
    this.transferForm.resetForm(); // reset form after submission
  },
  error => {
    console.error(error);
    console.log(error.error);
    this.notificationService.showError('Transfer failed! Please try again later.');
  }
);

  }

}
