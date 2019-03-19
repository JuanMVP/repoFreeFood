import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { UserListComponent } from './user-list/user-list.component';
import { RestaurantsListComponent } from './restaurants-list/restaurants-list.component';

export const DashboardRoutes: Routes = [{
  path: 'home',
  component: DashboardComponent
},{
  path:'usuarios',
  component: UserListComponent
},{
  path:'restaurantes',
  component: RestaurantsListComponent
}];
