import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
    path: string;
    title: string;
    Transactions: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  Transactions: 'design_app', class: '' },
    { path: '/transactions', title: 'Transactions',  Transactions:'education_atom', class: '' },
   
    { path: '/notifications', title: 'Notifications',  Transactions:'ui-1_bell-53', class: '' },

    { path: '/user-profile', title: 'User Profile',  Transactions:'users_single-02', class: '' },
    { path: '/table-list', title: 'Table List',  Transactions:'design_bullet-list-67', class: '' },
    { path: '/typography', title: 'Typography',  Transactions:'text_caps-small', class: '' },
    { path: '/upgrade', title: 'Upgrade to PRO',  Transactions:'objects_spaceship', class: 'active active-pro' }

];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ( window.innerWidth > 991) {
          return false;
      }
      return true;
  };
}
