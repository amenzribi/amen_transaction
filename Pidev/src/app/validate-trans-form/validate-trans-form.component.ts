import { Component, OnInit } from '@angular/core';
import { Transaction } from '../Model/transaction';
import { TransactionsService } from '../transactions.service';
import { NotificationServiceService } from '../notification-service.service';
@Component({
  selector: 'app-validate-trans-form',
  templateUrl: './validate-trans-form.component.html',
  styleUrls: ['./validate-trans-form.component.css']
})
export class ValidateTransFormComponent implements OnInit {
/*
  validationCode: string;
  message: string;
  success: boolean;
  constructor(private transactionService: TransactionsService) { }

  ngOnInit(): void {
  }
 


  onSubmit() {
    this.transactionService.validateTransaction(this.validationCode)
      .subscribe(
        data => {
          console.log(data); // log the data returned by the API
          this.message = 'Transaction validated successfully.';
          this.success = true;
        },
        error => {
          console.log(error); // log the error returned by the API
          this.message = error;
          this.success = false;
        }
      );
  }
  */

  validationCode: string;
  message: string;
  success: boolean;

  constructor(
    private transactionService: TransactionsService, private notificationService: NotificationServiceService ) { }

  ngOnInit(): void {
  }
  onSubmit() {
    if (this.validationCode.length === 6) {
      this.transactionService.validateTransaction(this.validationCode)
        .subscribe(
          (data: any) => {
            if (data && data.message) {
              console.log(data); // Log the entire response for debugging purposes
              this.message = data.message; // Assuming the message is in the "message" property
              this.success = true;
              this.notificationService.showSuccess(this.message);
              this.validationCode = ''; // Reset validation code input
            } else {
              console.log(data); // Log the entire response for debugging purposes
              this.message = 'Invalid response format.';
              this.success = false;
              this.notificationService.showError(this.message);
            }
          },
          error => {
            console.log(error); // Log the error returned by the API
           // this.message = 'Error occurred while validating transaction.';
          //  this.success = false;
            // this.notificationService.showError(this.message);
          }
        );
    } else {
     // this.notificationService.showError('Validation code should be 6 characters long.');
    }
  }
  
}
