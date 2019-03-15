import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { Usuario } from 'src/app/interfaces/usuario.interface';
import { UsuarioService } from 'src/app/services/usuario.service';
import { AddUserDto } from 'src/app/dto/add-user-dto';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {
  addUser: FormGroup;
  passwordControl = new FormControl('', [Validators.required, Validators.minLength(6)]);


  constructor(public dialogRef: MatDialogRef<AddUserComponent>, public snackBar: MatSnackBar,private userService: UsuarioService) { }

  ngOnInit() {


    this.addUser = new FormGroup({
      name: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required]),
      password: this.passwordControl,
      role: new FormControl("", [Validators.required]),
    });

  }

  addNewUser(){
    this.userService.addUser(<AddUserDto>this.addUser.value).subscribe(usuario => {
      console.log(this.addUser.value);
      this.dialogRef.close();
      this.snackBar.open('Usuario Creado', 'x',{
        duration:1500,
        verticalPosition: 'top'
      })
    })
  }


}
