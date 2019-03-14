import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginServiceService } from './login-service.service';
import { Usuario } from '../interfaces/usuario.interface';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient, private loginService: LoginServiceService) { }

  getAllUsers(): Observable<Usuario[]>{
    const requestOptions = {
      headers: new HttpHeaders ({
        'Content-Type': 'application/json',
                'Authorization': `Bearer ${this.loginService.getToken()}`,
                'Access-Control-Allow-Origin': '*'
      })
    };

    return this.http.get<Usuario[]>(`${environment.ApiUrl}/users`, requestOptions)

  }

}
