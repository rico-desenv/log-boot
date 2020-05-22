import { Component, OnInit } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { FormControl, FormGroup, PatternValidator } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Routes, RouterModule, ActivatedRoute, Params, Router } from '@angular/router';
import { Log } from '../log';
import { LogService } from '../log.service';
import { Observable, combineLatest } from 'rxjs';
import { map } from 'rxjs/operators';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-log-form',
  templateUrl: './log-form.component.html',
  styleUrls: ['./log-form.component.css'],
  providers: [DatePipe]
})
export class LogFormComponent implements OnInit {
  params: any;
  datePipe: DatePipe;
  dataParsed: any;
  log = new FormGroup({     
    data: new FormControl(''),
    ip: new FormControl(''),
    request: new FormControl(''),
    status: new FormControl(''),
    userAgent: new FormControl('')
  })
  constructor(private logService: LogService, private router: Router,) { 
    
    this.params=this.router.getCurrentNavigation().finalUrl.queryParams;    
    if (this.params.data==undefined) return;
    
    let l = this.params.data;
    let log2 = JSON.parse(l);
    let e: Log = new Log();
    Object.assign(e, log2);
    console.log(e);

    this.datePipe = new DatePipe('en-US');
    this.dataParsed=this.datePipe.transform(e.data, 'yyyy-MM-dd');
    console.log(this.dataParsed);
    this.log.setValue({ip: e.ip, request: e.request, data: this.dataParsed, status: e.status, userAgent: e.userAgent});

  }

  ngOnInit(): void {


  }
  
  obter() {
    this.params = this.log.value;
  }

  salvar() {
    console.log(this.log.value);
    this.logService.add(this.log.value).subscribe(
      res => {
      console.log(res);
      this.router.navigate(['/logs']);
    });
  }

  voltar() {
    this.router.navigate(['/logs']);
  }
}


