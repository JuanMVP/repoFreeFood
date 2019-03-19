import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { EditRecipeDto } from 'src/app/dto/edit-recipe-dto';
import { EditRestaurantDto } from 'src/app/dto/edit-restaurant-dto';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';



@Component({
  selector: 'app-edit-restaurant',
  templateUrl: './edit-restaurant.component.html',
  styleUrls: ['./edit-restaurant.component.scss']
})
export class EditRestaurantComponent implements OnInit {
  editRestaurant: FormGroup;

  id: String;
  name: String;
  address: String;
  intolerance: String;
  timetable: String;

  constructor( private restauranteService: RestaurantService,@Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<EditRestaurantComponent>,public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.id = this.data.element.id;
    this.name = this.data.element.name;
    this.address = this.data.element.address;
    this.intolerance = this.data.element.intolerance;
    this.timetable = this.data.element.timetable;

    this.editRestaurant = new FormGroup({
      name: new FormControl("", [Validators.required]),
      address: new FormControl("", [Validators.required]),
      intolerance: new FormControl("", [Validators.required]),
      timetable: new FormControl("", [Validators.required]),
    });
  }


  editOneRestaurant(){
    this.restauranteService.editRestaurant(this.id,<EditRestaurantDto>this.editRestaurant.value).subscribe(editRestaurant =>{
      this.dialogRef.close();
      this.snackBar.open('Restaurante Editado','x',{
        duration: 1500,
        verticalPosition: 'top'
      });
    })
  }



}
