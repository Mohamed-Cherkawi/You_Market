import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./modules/login/login.component";
import {RegisterComponent} from "./modules/register/register.component";
import {AuthenticationGuard} from "./guards/authentication.guard";

const routes: Routes = [
  { path : '', redirectTo: '/login', pathMatch: 'full' },
  { path : 'login' , component : LoginComponent , canActivate : [AuthenticationGuard] },
  { path : 'register' , component : RegisterComponent , canActivate : [AuthenticationGuard] },
  { path : 'home' , component : RegisterComponent  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
