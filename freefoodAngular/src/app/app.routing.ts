import { Routes } from '@angular/router';

import { AdminLayoutComponent, AuthLayoutComponent } from './core';

export const AppRoutes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {
    path: '',
    children: [{
      path: '',
      loadChildren: './session/session.module#SessionModule'
    }]
  },
  {
    path: 'home',
    component: AdminLayoutComponent,
    children: [{
      path: '',
      loadChildren: './dashboard/dashboard.module#DashboardModule'
    }]
  },{
  path: '**',
  redirectTo: 'session/404'
}];
