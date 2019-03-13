import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule, MatCardModule, MatButtonModule, MatListModule, MatProgressBarModule, MatMenuModule, MatSnackBar, MatDialog, MatSnackBarModule, MatDialogModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { DialogDeleteRecipeComponent } from './dialog-delete-recipe/dialog-delete-recipe.component';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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
    FlexLayoutModule
  ],
  declarations: [ DashboardComponent, DialogDeleteRecipeComponent, AddRecipeComponent ],
  entryComponents: [DialogDeleteRecipeComponent,AddRecipeComponent]
})

export class DashboardModule {}
