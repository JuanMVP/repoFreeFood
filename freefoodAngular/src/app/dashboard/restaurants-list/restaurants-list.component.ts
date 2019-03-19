import { Component, OnInit } from '@angular/core';
import { RestauranteResponse } from 'src/app/interfaces/RestauranteResponse.interface';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { MatSnackBar, MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { AddRestaurantComponent } from '../add-restaurant/add-restaurant.component';
import { EditRestaurantComponent } from '../edit-restaurant/edit-restaurant.component';
import { DialogDeleteRestaurantComponent } from '../dialog-delete-restaurant/dialog-delete-restaurant.component';

@Component({
  selector: 'app-restaurants-list',
  templateUrl: './restaurants-list.component.html',
  styleUrls: ['./restaurants-list.component.css']
})
export class RestaurantsListComponent implements OnInit {
  arrayRestaurantes: RestauranteResponse[];

  constructor(private restauranteService: RestaurantService,
    public snackBar: MatSnackBar,
    public dialog: MatDialog,
    private router: Router,) { }

  ngOnInit() {
    this.getAllRestaurants('Restaurantes Cargados Correctamente');

  }

  getAllRestaurants(mensaje: string){
    this.restauranteService.listaRestaurantes().subscribe(listaRestaurantes =>{
      console.log(listaRestaurantes);
      this.arrayRestaurantes = listaRestaurantes
      
    }, error =>{
      console.error(error);
      this.snackBar.open('Error Al Cargar Los Restaurantes', 'Cerrar',{
        duration:2000,
      });
    })
  }

  public openUploadDialog() {
    const dialogRef = this.dialog.open(AddRestaurantComponent,
      {
        width: '500px',
        data: { id: 1 }
      });

    dialogRef.afterClosed().subscribe(result => {
      this.snackBar.open(
        'El fichero se subió correctamente', 'Cerrar', {
        duration: 3000,
        verticalPosition: 'top'
      });
    });
  }

  openDialogEditRestaurant(restaurant: RestauranteResponse){
    const dialogEditRecipe = this.dialog.open(EditRestaurantComponent,{
      height: "40%",
      data: {
        element: restaurant
      }
    });
  
  dialogEditRecipe.afterClosed().subscribe(resultado =>{
    
    this.getAllRestaurants("");
  })
  }

  openDialogDeleteRestaurant(restaurant: RestauranteResponse){
    const dialogDeleteRecipe = this.dialog.open(DialogDeleteRestaurantComponent,{
      height: "40%",
      data: {
        element: restaurant
      }
    });
    dialogDeleteRecipe.afterClosed().subscribe(result => {
      this.getAllRestaurants('Cargado Correctamente');
    }, error =>{
      console.log(error);
    });
  }


}
