import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-create-listing',
  templateUrl: './create-listing.component.html',
  styleUrls: ['./create-listing.component.css']
})
export class CreateListingComponent {
  listingsTypeProperties = [
    { title : 'Item for sale' , description : 'Create a single listing<br>for one or more items to sell'  , imageName: 'open-box' , alt : 'Items Image' , redirectTo: 'item'},
    { title : 'Vehicle for sale' , description : 'Sell a car , truck or <br> other type of vehicle' , imageName: 'car' , alt : 'Car Image' , redirectTo: 'vehicle'},
    { title : 'Home for Rent' , description : 'List a house or apartment<br>for rent' , imageName: 'home' , alt : 'House Image' , redirectTo: 'home'},
  ];

  constructor(private router: Router , private activatedRoute: ActivatedRoute) { }

  redirectToHome(): void {
    this.router.navigate(['/home']);
  }
  redirectToRoute(route: string): void {
    this.router.navigate([route] , { relativeTo: this.activatedRoute });
  }
}
