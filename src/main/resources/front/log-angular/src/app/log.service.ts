import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Log } from './log';
import { from } from 'rxjs';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
 
@Injectable()
export class LogService {

  private logUrl: string;
  private logUrlFind: string;

  constructor(private http: HttpClient) {
    this.logUrl = environment.apiUrl;
  }

  public findAll(l: string): Observable<Log[]> {
    this.logUrlFind = this.logUrl + "/listar?ip=" + l;
    console.log(this.logUrlFind);
    return this.http.get<Log[]>(this.logUrlFind);
  }

  public add(l: Log):  Observable<Log> {
    return this.http.post<Log>(this.logUrl + "/add?ip=" + l.ip + "&request=" + l.request +
    "&data=" + l.data + "&status=" + l.status + "&useragent=" + l.userAgent, l);
  }

  public apagar(data: Log):  Observable<Log> {
    return this.http.post<Log>(this.logUrl + "/apagar?id=" + data.id, data);
  }
     

}