import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { AddRestaurantDto } from 'src/app/dto/add-restaurant-dto';
import { forkJoin } from 'rxjs';
import { UploadService } from 'src/app/services/upload-service.service';
import { Intolerance } from 'src/app/interfaces/Intolerance-response-interface';

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent implements OnInit {
  form: FormGroup;
  allIntolerances: Intolerance[];
  intoleranceSelected;

  @ViewChild('file') file;
  progress;
  canBeClosed = true;
  primaryButtonText = 'Subir';
  showCancelButton = true;
  uploading = false;
  uploadSuccessful = false;
  public files: Set<File> = new Set();

  constructor(private fb: FormBuilder,public dialogRef: MatDialogRef<AddRestaurantComponent>, public snackBar: MatSnackBar,private restauranteService: RestaurantService,@Inject(MAT_DIALOG_DATA) public data: any, private uploadService: UploadService) { }

  ngOnInit() {
    this.form = this.fb.group({
      name: [null, Validators.compose([Validators.required])],
      address: [null, Validators.compose([Validators.required])],
      intolerance: [null, Validators.compose([Validators.required])],
      timetable: [null, Validators.compose([Validators.required])],
    });
    this.getAllIntolerances();
  }
  

  
  addNewRestaurant(){
    this.restauranteService.addRestaurant(<AddRestaurantDto>this.form.value).subscribe(restaurante =>{
      this.dialogRef.close();
      this.snackBar.open('Restaurante Creado','x',{
        duration: 1500,
        verticalPosition: 'top'
      });
    })
  }

  getAllIntolerances(){
    this.restauranteService.getAllIntolerances().subscribe(listaIntolerancias =>{
      this.allIntolerances = listaIntolerancias.rows

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
      this.progress = this.uploadService.upload(
        this.files, this.form.value);

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


}
