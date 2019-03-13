import { Component, OnInit } from '@angular/core';
import { ListaRecetasResponse } from 'src/app/interfaces/ListaRecetasResponse.interface';
import { ListaRecetasServiceService } from 'src/app/services/lista-recetas-service.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-dialog-delete-recipe',
  templateUrl: './dialog-delete-recipe.component.html',
  styleUrls: ['./dialog-delete-recipe.component.scss']
})
export class DialogDeleteRecipeComponent implements OnInit {
 arrayRecetas: ListaRecetasResponse[];
 palabraBorrar: string;

  constructor(public data: any, private recetaService: ListaRecetasServiceService,
    public dialogRef: MatDialogRef<DialogDeleteRecipeComponent>) { }

  ngOnInit() {
  }


getRecetas(){
  this.recetaService.listaRecetasResponse().subscribe(listaRecursos => {
    this.arrayRecetas = listaRecursos;
  },error => {
    console.log('Error');

  });
}

  doEliminarRecipe(id){
    console.log(this.data.id)
    this.recetaService.deleteRecipe(this.data.id).subscribe( () => {

      this.closeDialog()
    });
  }


  validarDelete():boolean{
    let validar = true;
    if(this.palabraBorrar != 'ELIMINAR'){
      validar = false;
    }
    return validar;
  }

  closeDialog(){
    this.dialogRef.close();
  }


}
