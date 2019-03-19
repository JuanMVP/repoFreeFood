import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RestauranteResponse } from '../interfaces/RestauranteResponse.interface';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AddRestaurantDto } from '../dto/add-restaurant-dto';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) { }


  listaRestaurantes(): Observable<RestauranteResponse[]>{
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      })
    };

    return this.http.get<RestauranteResponse[]>(`${environment.ApiUrl}/restaurants`, requestOptions);

  }

  addRestaurant(addRestaurant: AddRestaurantDto){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };

    return this.http.post<RestauranteResponse>(`${environment.ApiUrl}/restaurants`, addRestaurant, requestOptions);

  }


  editRestaurant(id : String, editRestaurant: AddRestaurantDto){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };

    return this.http.put<RestauranteResponse>(`${environment.ApiUrl}/restaurants/${id}`, editRestaurant, requestOptions);

  }


  deleteOneRestaurant(id: String){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      })
    };

    return this.http.delete(`${environment.ApiUrl}/restaurants/${id}`, requestOptions);

  }


}
