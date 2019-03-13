import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CountRowListResponse } from '../interfaces/CountRowList';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ListaRecetasServiceService {

  constructor(private http: HttpClient) { }

  listaRecetasResponse(): Observable<CountRowListResponse>{
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<CountRowListResponse>(`${environment.ApiUrl}/recipes`, requestOptions);
  }


  deleteRecipe(id : string): Observable<any>{
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.delete<any>(`${environment.ApiUrl}/recipes/${id}`, requestOptions);
  }



}
