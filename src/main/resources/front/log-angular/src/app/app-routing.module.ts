import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogListComponent } from './log-list/log-list.component';
import { LogFormComponent } from './log-form/log-form.component';
import { Log } from './log';
 
const routes: Routes = [
  { path: 'logs', component: LogListComponent },
  { path: 'form', component: LogFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
