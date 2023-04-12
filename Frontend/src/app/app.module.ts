import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './modules/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterOutlet} from "@angular/router";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {JWT_OPTIONS, JwtModule} from "@auth0/angular-jwt";
import {RegisterComponent} from "./modules/register/register.component";
import {TokenInterceptor} from "./interceptors/token.interceptor";
import {AuthenticationGuard} from "./guards/authentication.guard";
import { HeaderComponent } from './layouts/header/header.component';
import { HomeComponent } from './modules/home/home.component';
import { BottomSheetComponent } from './layouts/bottom-sheet/bottom-sheet.component';
import { CategoryComponent } from './layouts/home/category/category.component';
import { CreateListingComponent } from './modules/create-listing/create-listing.component';
import { ItemComponent } from './modules/listings/item/item.component';
import { CreateContainerComponent } from './modules/create-container/create-container.component';
import {MatSelectModule} from "@angular/material/select";
import {MatGridListModule} from '@angular/material/grid-list';
import { DragDirective } from './drag.directive';
import { SellingComponent } from './modules/selling/selling.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    HomeComponent,
    BottomSheetComponent,
    CategoryComponent,
    CreateListingComponent,
    ItemComponent,
    CreateContainerComponent,
    DragDirective,
    SellingComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        RouterOutlet,
        ReactiveFormsModule,
        MatCardModule,
        BrowserAnimationsModule,
        MatInputModule,
        MatIconModule,
        MatGridListModule,
        MatSelectModule,
      JwtModule.forRoot({
            jwtOptionsProvider: { // this provider is necessary to use the librairy that decoes and manages the JWT
                provide: JWT_OPTIONS,
                useValue: {}
            }
        }),
    ],
  providers: [
    // Interceptors providers
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    // Guards providers
    AuthenticationGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
