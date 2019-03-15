import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginServiceService } from './login-service.service';
import { Usuario } from '../interfaces/usuario.interface';
import { Observable } from 'rxjs';
import { CountRowListResponse } from '../interfaces/CountRowList';
import { AddUserDto } from '../dto/add-user-dto';
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

  addUser(addUser: AddUserDto){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        
      })
    };
    return this.http.post<Usuario>(`${environment.ApiUrl}/users?access_token=${environment.masterKey}`,addUser, requestOptions)
  }

  editUser(id: String, editUser: AddUserDto){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
  };
  return this.http.put<Usuario>(`${environment.ApiUrl}/users/${id}`,editUser,requestOptions)
}


  deleteOneUser(id: string){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.delete(`${environment.ApiUrl}/users/${id}`, requestOptions);
  }


}
