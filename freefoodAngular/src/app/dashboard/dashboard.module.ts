import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule, MatCardModule, MatButtonModule, MatListModule, MatProgressBarModule, MatMenuModule, MatSnackBar, MatDialog, MatSnackBarModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatTableModule, MatPaginatorModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { DialogDeleteRecipeComponent } from './dialog-delete-recipe/dialog-delete-recipe.component';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditRecipeComponent } from './edit-recipe/edit-recipe.component';
import { UserListComponent } from './user-list/user-list.component';
import { ListaRecetasServiceService } from '../services/lista-recetas-service.service';
import { UsuarioService } from '../services/usuario.service';
import { DialogUserDeleteComponent } from './dialog-user-delete/dialog-user-delete.component';
import { DialogEditUsuarioComponent } from './dialog-edit-usuario/dialog-edit-usuario.component';
import { AddUserComponent } from './add-user/add-user.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { RestaurantsListComponent } from './restaurants-list/restaurants-list.component';
import { RestaurantService } from '../services/restaurant.service';
import { EditRestaurantComponent } from './edit-restaurant/edit-restaurant.component';
import { DialogDeleteRestaurantComponent } from './dialog-delete-restaurant/dialog-delete-restaurant.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(DashboardRoutes),
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatListModule,
    MatProgressBarModule,
    MatMenuModule,
    MatSnackBarModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    FlexLayoutModule
  ],
  declarations: [ DashboardComponent, DialogDeleteRecipeComponent, AddRecipeComponent, EditRecipeComponent, UserListComponent, DialogUserDeleteComponent, DialogEditUsuarioComponent, AddUserComponent, AddRestaurantComponent, RestaurantsListComponent, EditRestaurantComponent, DialogDeleteRestaurantComponent ],
  providers: [
    ListaRecetasServiceService,UsuarioService,RestaurantService
  ],
  entryComponents: [DialogDeleteRecipeComponent,EditRecipeComponent,AddRecipeComponent, UserListComponent,DialogUserDeleteComponent,DialogEditUsuarioComponent,AddUserComponent,AddRestaurantComponent, RestaurantsListComponent,EditRestaurantComponent,DialogDeleteRestaurantComponent]
})

export class DashboardModule {}
