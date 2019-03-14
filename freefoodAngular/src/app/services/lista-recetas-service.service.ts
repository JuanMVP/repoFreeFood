import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CountRowListResponse } from '../interfaces/CountRowList';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ListaRecetasResponse } from '../interfaces/ListaRecetasResponse.interface';
import { AddRecipeDto } from '../dto/add-recipe-dto';

const recipesUrl = `${environment.ApiUrl}/recipes`;

@Injectable({
  providedIn: 'root'
})
export class ListaRecetasServiceService {

  constructor(private http: HttpClient) { }

  listaRecetasResponse(): Observable<ListaRecetasResponse[]>{
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.get<ListaRecetasResponse[]>(`${environment.ApiUrl}/recipes`, requestOptions);
  }

  addRecipe(addRecipe: AddRecipeDto){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.post<ListaRecetasResponse>(`${environment.ApiUrl}/recipes`, addRecipe, requestOptions);
  }

  editRecipe(id: String, editRecipe: AddRecipeDto){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.put<ListaRecetasResponse>(`${recipesUrl}/${id}`, editRecipe, requestOptions);
  }


  /*deleteRecipe(id : string){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.delete(`${environment.ApiUrl}/recipes/${id}`, requestOptions);
  }*/

  deleteOneRecipe(id: string) {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.delete(`${recipesUrl}/${id}`, requestOptions);
  }



}
