import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpEventType, HttpResponse } from '@angular/common/http';
import { CountRowListResponse } from '../interfaces/CountRowList';
import { environment } from 'src/environments/environment';
import { Observable, Subject } from 'rxjs';
import { ListaRecetasResponse } from '../interfaces/ListaRecetasResponse.interface';
import { AddRecipeDto } from '../dto/add-recipe-dto';

const recipesUrl = `${environment.ApiUrl}/recipes`;

@Injectable({
  providedIn: 'root'
})
export class ListaRecetasServiceService {
  uploadUrl: string;

  constructor(private http: HttpClient) { }

  public upload(files: Set<File>, idCanto: number): { [key: string]: Observable<number> } {
    // this will be the our resulting map
    this.uploadUrl = `https://free-food-api.herokuapp.com/photorecipes?access_token=${localStorage.getItem('token')}`;
    const status = {};

    files.forEach(file => {
      // create a new multipart-form for every file
      const formData: FormData = new FormData();
      formData.append('foto', file, file.name);
      formData.append('nombre', 'Receta Sana');
      formData.append('recipe', '5c88fff7551c6100224c4cc3');

      // create a http-post request and pass the form
      // tell it to report the upload progress
      const req = new HttpRequest('POST', this.uploadUrl, formData, {
        reportProgress: true,
      });

      // create a new progress-subject for every file
      const progress = new Subject<number>();

      // send the http-request and subscribe for progress-updates

      const startTime = new Date().getTime();
      this.http.request(req).subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          // calculate the progress percentage

          const percentDone = Math.round((100 * event.loaded) / event.total);
          // pass the percentage into the progress-stream
          progress.next(percentDone);
        } else if (event instanceof HttpResponse) {
          // Close the progress-stream if we get an answer form the API
          // The upload is complete
          progress.complete();
        }
      });

      // Save every progress-observable in a map of all observables
      status[file.name] = {
        progress: progress.asObservable()
      };
    });

    // return the map of progress.observables
    return status;
  }

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
