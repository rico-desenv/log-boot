import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LogListComponent } from './log-list/log-list.component';
import { LogService } from './log.service';
import { LogFormComponent } from './log-form/log-form.component';
import { LogDateFormat } from './log.date.format';
import { LogUploadService } from './log-upload.service';

@NgModule({
  declarations: [
    AppComponent, 
    LogListComponent,
    LogFormComponent,
    LogDateFormat
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule, 
    MatIconModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [LogService, LogUploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
