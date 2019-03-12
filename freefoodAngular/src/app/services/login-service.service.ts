import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDto } from '../dto/LoginDto';
import { LoginResponse } from '../interfaces/LoginResponse';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

/*declare var require: any;
const jwtDecode = require('jwt-decode');*/
const authUrl = `${environment.ApiUrl}/user`;

const requestOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin':'*'
  })
};


@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http: HttpClient, private router: Router) { }

  login(loginDto: LoginDto): Observable<LoginResponse> {
    
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ` + btoa(`${loginDto.email}:${loginDto.password}`),
        'Access-Control-Allow-Origin': '*'
      })
    };
    class Metakey {
      access_token: String;

      constructor(access_token: String) {
        this.access_token = access_token;
      }
    }
    const metaKey = new Metakey('RL0Pj065laUucm3JOcKTJ5JjOoDCYma2');
    return this.http.post<LoginResponse>(`${environment.ApiUrl}/auth`, metaKey, requestOptions);
  }

  public signin(signinDto: LoginDto): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${authUrl}/auth`, signinDto, requestOptions);
  }

  setLoginData(loginResponse: LoginResponse) {
    localStorage.setItem('token', loginResponse.token);
    localStorage.setItem('email', loginResponse.user.email);
    localStorage.setItem('name', loginResponse.user.name);
    localStorage.setItem('role', loginResponse.user.role);
    ;
}

getToken() {

  return localStorage.getItem('token');
}



}
