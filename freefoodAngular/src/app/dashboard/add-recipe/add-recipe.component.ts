import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { ListaRecetasServiceService } from 'src/app/services/lista-recetas-service.service';
import { AddRecipeDto } from 'src/app/dto/add-recipe-dto';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {
  addRecipe: FormGroup;
  @ViewChild('file') file;
  progress;
  canBeClosed = true;
  primaryButtonText = 'Subir';
  showCancelButton = true;
  uploading = false;
  uploadSuccessful = false;

  public files: Set<File> = new Set();

  constructor(public dialogRef: MatDialogRef<AddRecipeComponent>, public snackBar: MatSnackBar,private recetasService: ListaRecetasServiceService,@Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.addRecipe = new FormGroup({
      name: new FormControl("", [Validators.required]),
      description: new FormControl("", [Validators.required]),
      ingredients: new FormControl("", [Validators.required]),
      dinnerGuest: new FormControl("", [Validators.required]),
    });
  }

  onFilesAdded() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    this.files = new Set();
    for (const key in files) {
      if (!isNaN(parseInt(key, 10))) {
        this.files.add(files[key]);
      }
    }
  }


  closeDialog() {
    // if everything was uploaded already, just close the dialog
    if (this.uploadSuccessful) {
      return this.dialogRef.close();
    }

    // set the component state to "uploading"
    this.uploading = true;

    // start the upload and save the progress map
    this.progress = this.recetasService.upload(this.files, this.data.element.id);
    // tslint:disable-next-line:forin
    for (const key in this.progress) {
      this.progress[key].progress.subscribe(val => console.log(val));
    }

    // convert the progress map into an array
    const allProgressObservables = [];
    // tslint:disable-next-line:forin
    for (const key in this.progress) {
      allProgressObservables.push(this.progress[key].progress);
    }

    // Adjust the state variables

    // The OK-button should have the text "Finish" now
    this.primaryButtonText = 'Finalizar';

    // The dialog should not be closed while uploading
    this.canBeClosed = false;
    this.dialogRef.disableClose = true;

    // Hide the cancel-button
    this.showCancelButton = false;

    // When all progress-observables are completed...
    forkJoin(allProgressObservables).subscribe(end => {
      // ... the dialog can be closed again...
      this.canBeClosed = true;
      this.dialogRef.disableClose = false;

      // ... the upload was successful...
      this.uploadSuccessful = true;

      // ... and the component is no longer uploading
      this.uploading = false;

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
