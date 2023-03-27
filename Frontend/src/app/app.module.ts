import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginComponent} from "./modules/login/login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {HttpClientModule} from "@angular/common/http";
import {RouterOutlet} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {JWT_OPTIONS, JwtModule} from "@auth0/angular-jwt";
import { RegisterComponent } from './modules/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatInputModule,
        HttpClientModule,
        RouterOutlet,
        AppRoutingModule,
        JwtModule.forRoot({
            jwtOptionsProvider: { // this provider is necessary to use the librairy that decoes and manages the JWT
                provide: JWT_OPTIONS,
                useValue: {}
            }
        }),
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
