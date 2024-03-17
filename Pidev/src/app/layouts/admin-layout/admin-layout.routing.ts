import { Routes } from '@angular/router';



import { TableListComponent } from '../../table-list/table-list.component';

import { transactionComponent } from '../../transactions/transaction.component';


import {DepositFormComponent} from '../../deposit-form/deposit-form.component';

import {ValidateTransFormComponent} from '../../validate-trans-form/validate-trans-form.component';
import { WithdrawFormComponent } from '../../withdraw-form/withdraw-form.component';
import { TransferFormComponent } from '../../transfer-form/transfer-form.component';

export const AdminLayoutRoutes: Routes = [
 
  
    { path: 'table-list',     component: TableListComponent },

    { path: 'transactions',   component: transactionComponent },
   
    
    {path: 'deposit-form' , component: DepositFormComponent},
    
    {path: 'validate-trans-form' , component: ValidateTransFormComponent},
    {path: 'withdraw-form' , component: WithdrawFormComponent},
    {path: 'transfer-form' , component: TransferFormComponent}
    
];
