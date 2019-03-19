import { Component, OnInit, Inject } from '@angular/core';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AddRestaurantDto } from 'src/app/dto/add-restaurant-dto';

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent implements OnInit {
  addRestaurant: FormGroup;

  constructor(public dialogRef: MatDialogRef<AddRestaurantComponent>, public snackBar: MatSnackBar,private restauranteService: RestaurantService,@Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

    this.addRestaurant = new FormGroup({
      name: new FormControl("", [Validators.required]),
      description: new FormControl("", [Validators.required]),
      ingredients: new FormControl("", [Validators.required]),
      dinnerGuest: new FormControl("", [Validators.required]),
    });

  }

  addNewRestaurant(){
    this.restauranteService.addRestaurant(<AddRestaurantDto>this.addRestaurant.value).subscribe(restaurante =>{
      this.dialogRef.close();
      this.snackBar.open('Restaurante Creado','x',{
        duration: 1500,
        verticalPosition: 'top'
      });
    })
  }

}
