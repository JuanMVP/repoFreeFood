import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { ListaRecetasServiceService } from 'src/app/services/lista-recetas-service.service';
import { AddRecipeDto } from 'src/app/dto/add-recipe-dto';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {
  addRecipe: FormGroup;

  constructor(public dialogRef: MatDialogRef<AddRecipeComponent>, public snackBar: MatSnackBar,private recetasService: ListaRecetasServiceService) { }

  ngOnInit() {
    this.addRecipe = new FormGroup({
      name: new FormControl("", [Validators.required]),
      description: new FormControl("", [Validators.required]),
      ingredients: new FormControl("", [Validators.required]),
      dinnerGuest: new FormControl("", [Validators.required]),
    });
  }


  addNewRecipe(){
    this.recetasService.addRecipe(<AddRecipeDto>this.addRecipe.value).subscribe(receta => {
      this.dialogRef.close();
      this.snackBar.open('Receta Creada','x',{
        duration: 1500,
        verticalPosition: 'top'
      });
    })

  }


}
