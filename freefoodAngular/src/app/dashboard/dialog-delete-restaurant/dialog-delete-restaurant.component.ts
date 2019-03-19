import { Component, OnInit, Inject } from '@angular/core';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-delete-restaurant',
  templateUrl: './dialog-delete-restaurant.component.html',
  styleUrls: ['./dialog-delete-restaurant.component.scss']
})
export class DialogDeleteRestaurantComponent implements OnInit {

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<DialogDeleteRestaurantComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private restauranteService: RestaurantService
  ) {}

  ngOnInit() {
  }

  deleteRestaurant(){
    this.restauranteService
      .deleteOneRestaurant(this.data.element.id)
      .subscribe(
        resp => {
          this.dialogRef.close();
        },
        error => {
          console.log(error);
        }
      );
  }

}
