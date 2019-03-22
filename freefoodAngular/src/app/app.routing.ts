import { Routes } from '@angular/router';

import { AdminLayoutComponent, AuthLayoutComponent } from './core';

export const AppRoutes: Routes = [
  /*{path: '', redirectTo: 'session/signin', pathMatch: 'full'},
  {
    path: '',
    children: [{
      path: 'session',
      loadChildren: './session/session.module#SessionModule'
    }]
  },
  {
    path: '',
    component: AdminLayoutComponent,
    children: [{
      path: '',
      loadChildren: './dashboard/dashboard.module#DashboardModule'
    }]
  },{
  path: '**',
  redirectTo: 'session/404'
}];*/

 
{path: '', redirectTo: 'session/signin', pathMatch: 'full'},{
  path: 'session',
  component: AuthLayoutComponent,
  children: [{
    path: '',
    loadChildren: './session/session.module#SessionModule'
  }]
}, {
  path: '',
  component: AdminLayoutComponent,
  children: [{
    path: '',
    loadChildren: './dashboard/dashboard.module#DashboardModule'

  }]
}, ];
