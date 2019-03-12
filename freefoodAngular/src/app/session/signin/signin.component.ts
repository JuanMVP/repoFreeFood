import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { UserDto } from 'src/app/dto/UserDto';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { MatSnackBar } from '@angular/material';
import { LoginDto } from 'src/app/dto/LoginDto';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  user: UserDto;
  email: string;
  password: string;
  name: string;
  public loginForm: FormGroup;
  constructor(private loginService: LoginServiceService,
    private router: Router,
    private fb: FormBuilder, private snackBar: MatSnackBar) { }
    public isError = false;


  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, Validators.compose([Validators.required])], password: [null, Validators.compose([Validators.required])]
    });

    if (localStorage.getItem('token') == null) {
      this.router.navigate(['']);

    } else {
      this.router.navigate(['inicio'])
    }
  }

  doLogin() {



    const signinDto = new LoginDto(this.loginForm.get('email').value, this.loginForm.get('password').value);
    this.loginService.login(signinDto).subscribe(signinResp => {
      console.log(signinResp);
      this.loginService.setLoginData(signinResp);
      window.location.replace('home');
    }, error => {
      console.log('Error en petición de login');
    });
  }

  onSubmit() {
    this.router.navigate ( [ '/dashboard' ] );
  }

}
