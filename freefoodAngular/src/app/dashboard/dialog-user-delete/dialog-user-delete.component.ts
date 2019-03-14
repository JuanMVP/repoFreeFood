import { Component, OnInit, Inject } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-user-delete',
  templateUrl: './dialog-user-delete.component.html',
  styleUrls: ['./dialog-user-delete.component.scss']
})
export class DialogUserDeleteComponent implements OnInit {

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<DialogUserDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UsuarioService
  ) {}

  ngOnInit() {
  }


  deleteUser(){
    this.userService.deleteOneUser(this.data.element.id).subscribe( resp => {
      this.dialogRef.close();
    },error => {
      console.log(error);
    });
  }


}
