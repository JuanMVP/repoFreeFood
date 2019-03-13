import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ImageUploadDto } from '../dto/ImageUploadDto';
import { Observable } from 'rxjs';
import { ImagenResponse } from '../interfaces/ImagenResponse.interface';
import { HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ImgurUploadService {
  base64: String;
  

  constructor(private http: HttpClient) { }

  uploadImage(imagenDto: ImageUploadDto): Observable<ImagenResponse>{
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'multipart/form-data',
        'Authorization': `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjODYyYzkyMzQ0NDhiMDAxN2EzOTI4NiIsImlhdCI6MTU1MjQ2NDExMH0.2F4Js5I4AnWkPTJ9vSNVdHAeXv4hKwE6Rn7VOctQS88`

      })
    }

    return this.http.post<ImagenResponse>(`${environment.imgUr}/image`,imagenDto.image, requestOptions);

  }




}
