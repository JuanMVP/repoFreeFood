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
 
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private recetaService: ListaRecetasServiceService,
    public dialogRef: MatDialogRef<DialogDeleteRecipeComponent>, public snackBar: MatSnackBar) { }
    //name = this.data.element.name;
    confirmarBorrar: String;
  ngOnInit() {
  }


  doEliminarRecipe(){
    
    this.recetaService.deleteRecipe(this.data.id).subscribe( deleteResp => {
      console.log(this.data.id)

      this.closeDialog()
    
    });
  }


  validarDelete():boolean{
    let validar = true;
    if(this.confirmarBorrar != 'ELIMINAR'){
      validar = false;
    }
    return validar;
  }

  closeDialog(){
    this.dialogRef.close();
  }


}
