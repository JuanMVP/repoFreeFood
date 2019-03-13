import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { ListaRecetasServiceService } from 'src/app/services/lista-recetas-service.service';
import { EditRecipeDto } from 'src/app/dto/edit-recipe-dto';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.scss']
})
export class EditRecipeComponent implements OnInit {
  editRecipe: FormGroup;

  id: String;
  name: String;
  description: String;
  ingredients: String;
  dinnerGuest: Number;

  constructor( private recetaService: ListaRecetasServiceService,@Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<EditRecipeComponent>,public snackBar: MatSnackBar) { }


  ngOnInit() {
    this.id
    this.name = this.data.name;
    this.description = this.data.description;
    this.ingredients = this.data.ingredients;
    this.dinnerGuest = this.data.dinnerGuest;

    this.editRecipe = new FormGroup({
      name: new FormControl("", [Validators.required]),
      description: new FormControl("", [Validators.required]),
      ingredients: new FormControl("", [Validators.required]),
      dinnerGuest: new FormControl("", [Validators.required]),
    });

  }

  editOneRecipe(){
    this.recetaService.editRecipe(this.id,<EditRecipeDto>this.editRecipe.value).subscribe(editedRecipe =>{
      this.dialogRef.close();
      this.snackBar.open('Receta Creada','x',{
        duration: 1500,
        verticalPosition: 'top'
      });
    })
  }

}
