import { Injectable , NgModule, Component, OnInit, Input } from '@angular/core';
import { Log } from '../log';
import { LogService } from '../log.service';
import { LogFormComponent } from '../log-form/log-form.component';
import { Router } from '@angular/router'
import { MatIconModule } from '@angular/material/icon';
import { LogDateFormat } from '../log.date.format';
import { LogUploadService } from '../log-upload.service';

@Component({
  selector: 'log-list',
  templateUrl: './log-list.component.html',
  styleUrls: ['./log-list.component.css']
})
export class LogListComponent implements OnInit {
 
  logs: Log[];
  @Input() log: Log;
  ip: string;
  fileToUpload: File = null;

   constructor(private logService: LogService, private logUploadService: LogUploadService, 
    private router:Router) {
  }
 
  ngOnInit() {
    this.logService.findAll("").subscribe(data => {
      this.logs = data;
    });
  }

  fileInput(files: FileList) {
    this.fileToUpload = files.item(0);

    this.logUploadService.postFile(this.fileToUpload).subscribe(data => {
      }, error => {
        console.log(error);
      });
  }

  editar(l: Log) {
    this.router.navigate(['/form', { queryParams: { log: l} }]);
  }

  apagar(l: Log) {
    this.logService.apagar(l).subscribe(
      res => {
      console.log("Registro removido");
      this.logService.findAll("").subscribe(data => {
        this.logs = data;
      });      
    });
  }

  filtrar() {
    this.logService.findAll(this.ip).subscribe(data => {
      this.logs = data;
    });  
  }

}