import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./modules/login/login.component";
import {RegisterComponent} from "./modules/register/register.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {HomeComponent} from "./modules/home/home.component";
import {CreateListingComponent} from "./modules/create-listing/create-listing.component";
import {ItemComponent} from "./modules/listings/item/item.component";
import {CreateContainerComponent} from "./modules/create-container/create-container.component";
import {SellingComponent} from "./modules/selling/selling.component";

const routes: Routes = [
  { path : '', redirectTo: '/login', pathMatch: 'full' },
  { path : 'login' , component : LoginComponent , canActivate : [AuthenticationGuard] },
  { path : 'register' , component : RegisterComponent , canActivate : [AuthenticationGuard] },
  { path : 'home' , component : HomeComponent  },
  { path : 'selling' , component : SellingComponent  },
  { path : 'create' , component : CreateContainerComponent , children : [
      { path : '' , component :  CreateListingComponent },
      { path : 'item' , component :  ItemComponent },
    ]  },
  { path: '**', redirectTo : '/home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
