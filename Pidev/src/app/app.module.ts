import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';

import { DepositFormComponent } from './deposit-form/deposit-form.component';
import { ValidateTransFormComponent } from './validate-trans-form/validate-trans-form.component';
import { WithdrawFormComponent } from './withdraw-form/withdraw-form.component';
import { TransferFormComponent } from './transfer-form/transfer-form.component';
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    NgbModule,
    ToastrModule.forRoot()
   
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
   
    DepositFormComponent,
    ValidateTransFormComponent,
    WithdrawFormComponent,
    TransferFormComponent

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
