import { Component, OnInit } from '@angular/core';
import { CountRowListResponse } from '../interfaces/CountRowList';
import { ListaRecetasResponse } from '../interfaces/ListaRecetasResponse.interface';
import { MatDialog, MatSnackBar } from '@angular/material';
import { ListaRecetasServiceService } from '../services/lista-recetas-service.service';
import { Router } from '@angular/router';
import { DialogDeleteRecipeComponent } from './dialog-delete-recipe/dialog-delete-recipe.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  arrayRecetas: ListaRecetasResponse[];
  

  ngOnInit() {
    this.getAllRecipes('Recetas Cargadas Correctamente');
    if(localStorage.getItem('token') == null){
      this.router.navigate(['']);
    }

    this.getAllRecipes('Error');
    
  }
  

  constructor(private listaRecetasService: ListaRecetasServiceService,
    public snackBar: MatSnackBar,
    public dialog: MatDialog,
    private router: Router,) { }

    getAllRecipes(mensaje: string){
      this.listaRecetasService.listaRecetasResponse().subscribe(listaRecipes => {
        console.log(listaRecipes);
        this.arrayRecetas = listaRecipes
        
      }, error =>{
        console.error(error);
        this.snackBar.open('Error Al Cargar Las Recetas', 'Cerrar',{
          duration:2000,
        });
      });
    }


    openDialogDeleteRecipe(recipe){
      const dialogDeleteRecipe = this.dialog.open(DialogDeleteRecipeComponent,{
        data: {
          id: recipe.id,
          nombre: recipe.name
        }
      });
      dialogDeleteRecipe.afterClosed().subscribe(result => {
        this.getAllRecipes('Cargado Correctamente');
      })
    }

}
