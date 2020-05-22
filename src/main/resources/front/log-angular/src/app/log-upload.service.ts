import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { RequestOptions } from '@angular/http';
import { Log } from './log';
import { from } from 'rxjs';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
 
@Injectable()
export class LogUploadService {

    private logUrl: string;
    httpClient: HttpClient;

    constructor(private http: HttpClient) {
        this.logUrl = environment.apiUrl;
    }

    postFile(fileToUpload: File)  {
        const endpoint = this.logUrl + "/arquivo";
        const formData: FormData = new FormData();
        formData.append('file', fileToUpload, fileToUpload.name);
            
        return this.http.post(endpoint, formData, {
            reportProgress: true,
            observe: 'events'
            }).pipe()        
    }
}