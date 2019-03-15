import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { EditUserDto } from 'src/app/dto/edit-user-dto';

@Component({
  selector: 'app-dialog-edit-usuario',
  templateUrl: './dialog-edit-usuario.component.html',
  styleUrls: ['./dialog-edit-usuario.component.scss']
})
export class DialogEditUsuarioComponent implements OnInit {
  editUser: FormGroup;
  id: String;
  name: String;
  email: String;
  passwordControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  constructor( private userService: UsuarioService,@Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<DialogEditUsuarioComponent>,public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.id = this.data.element.id;
    this.name = this.data.element.name;
    this.email = this.data.element.email;
    this.passwordControl = this.data.element.password;


    this.editUser = new FormGroup({
      name: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required]),
      password: this.passwordControl,
    });
  }

  editOneUser(){
    this.userService.editUser(this.id,<EditUserDto>this.editUser.value).subscribe(editedRecipe =>{
      this.dialogRef.close();
      this.snackBar.open('Receta Creada','x',{
        duration: 1500,
        verticalPosition: 'top'
      });
    })
  }

}
