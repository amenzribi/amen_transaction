import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationServiceService {

  
  constructor(private toastr: ToastrService) { }

  showSuccess(message: string, title?: string) {
    console.log('Show Success:', message, title);
    this.toastr.success(message, title, {
      timeOut: 3000,
      progressBar: true,
      closeButton: true,
      positionClass: 'toast-top-right',
    });
   //this.toastr.success(message, title);
  }

  showError(message: string, title?: string) {
    this.toastr.error(message, title);
  }

  showWarning(message: string, title?: string) {
    this.toastr.warning(message, title);
  }

  showInfo(message: string, title?: string) {
    this.toastr.info(message, title);
  }
}

