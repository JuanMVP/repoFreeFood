import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpRequest, HttpHeaders, HttpEventType, HttpResponse } from '@angular/common/http';
import { AddRestaurantDto } from '../dto/add-restaurant-dto';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  uploadUrl: string;
  token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjOGI2NmZlMDc5ZmIzMjBlYTExYTQ5NiIsImlhdCI6MTU1Mjk4Nzc2OH0.TQ_T7-JmGxLQ8f2Rcglzd4hdfoIxXLEJDgLfrx_fSIg';

  constructor(private http: HttpClient) { }

  public upload(files: Set<File>, form: AddRestaurantDto): { [key: string]: Observable<number> } {
    // this will be the our resulting map
    this.uploadUrl = `https://free-food-api.herokuapp.com/restaurants?access_token=${this.token}`;
    const status = {};

    files.forEach(file => {
      // create a new multipart-form for every file
      const formData: FormData = new FormData();
      formData.append('picture', file, file.name);
      formData.append('nombre', form.name);
      formData.append('direccion', form.address);
      formData.append('intolerancia', form.intolerance);
      formData.append('Horario y Telefono', form.timetable);
      

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
}
