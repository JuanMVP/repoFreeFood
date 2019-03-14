import { Component, OnInit, Inject } from '@angular/core';
import { ListaRecetasResponse } from 'src/app/interfaces/ListaRecetasResponse.interface';
import { ListaRecetasServiceService } from 'src/app/services/lista-recetas-service.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-dialog-delete-recipe',
  templateUrl: './dialog-delete-recipe.component.html',
  styleUrls: ['./dialog-delete-recipe.component.scss']
})
export class DialogDeleteRecipeComponent implements OnInit {
 
  
  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<DialogDeleteRecipeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private recipeService: ListaRecetasServiceService
  ) {}

  //name = this.data.element.name;
  palabraBorrar: string;

  ngOnInit() {}

  deleteRecipe() {
    this.recipeService
      .deleteOneRecipe(this.data.element.id)
      .subscribe(
        resp => {
          this.dialogRef.close();
        },
        error => {
          console.log(error);
        }
      );
  }

  validarDelete(): boolean {
    let validar = true;

    if (this.palabraBorrar != "ELIMINAR") {
      validar = false;
    }
    return validar;
  }


}
