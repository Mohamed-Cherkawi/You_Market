import {Component, OnInit, ViewChild} from '@angular/core';
import {BottomSheetComponent} from "../../layouts/bottom-sheet/bottom-sheet.component";
import {Router} from "@angular/router";
import {ListingService} from "../../services/listing.service";
import {Listing} from "../../models/listings/Listing.model";
import {ResponseEntity} from "../../utils/response-entity.model";
import {HttpErrorResponse} from "@angular/common/http";
import Swal from "sweetalert2";
import {ImageProcessingService} from "../../services/image-processing.service";
import {map} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  @ViewChild(BottomSheetComponent) bottomSheetComponent!: BottomSheetComponent ;

  links = [
    { svgIconName: 'store' , linkName : 'Browse all' , imgAlt : 'Store Icon' , class: 'linkActive' , iconColor: 'iconBackground'},
    { svgIconName: 'bell' , linkName : 'Notifications' , imgAlt : 'Notification Icon' , class: '' , iconColor: ''},
    { svgIconName: 'inbox' , linkName : 'Inbox' , imgAlt : 'Inbox Icon' , class: '' , iconColor: ''},
    { svgIconName: 'bag-shopping' , linkName : 'Buying' , imgAlt : 'Shopping Bag Icon' , class: '' , iconColor: ''},
    { svgIconName: 'tag' , linkName : 'Selling' , imgAlt : 'Tag Icon' , class: '' , iconColor: ''},
  ];
  categories = [
    { svgIconName: 'car' , linkName : 'Vehicles' , imgAlt : 'Car Icon' },
    { svgIconName: 'home' , linkName : 'Property Rentals' , imgAlt : 'Home Icon' },
    { svgIconName: 'shirt' , linkName : 'Apparel' , imgAlt : 'Shirt Icon' },
    { svgIconName: 'mobile' , linkName : 'Electronics' , imgAlt : 'Mobile Icon' },
    { svgIconName: 'camera' , linkName : 'Entertainment' , imgAlt : 'Camera Icon' },
    { svgIconName: 'heart' , linkName : 'Family' , imgAlt : 'Heart Icon' },
    { svgIconName: 'hand-heart' , linkName : 'Free Stuff' , imgAlt : 'Hand giving heart Icon' },
    { svgIconName: 'music' , linkName : 'Musical Instruments' , imgAlt : 'Music Icon' },
    { svgIconName: 'game-controller' , linkName : 'Toys & Games' , imgAlt : 'Game Controller Icon' },
    { svgIconName: 'run' , linkName : 'Sporting Goods' , imgAlt : 'A Man Running Icon' },
  ];
  listings : Listing[] = [];
  temp: string = '';

  constructor(private router: Router,
              private listingService: ListingService,
              private imageProcessingService: ImageProcessingService) {}

  ngOnInit(): void {
    this.getListings();
  }

  getListings(): void {
    this.listingService.getAllListings()
      // .pipe(
      //   map((x:ResponseEntity<Listing[]>) => x,map((listing: ResponseEntity<Listing>) => this.imageProcessingService.createImages(listing.data)))
      // ) // performing some bussines logic code before subscribing
      .subscribe(
      {
        next:
          (response: ResponseEntity<Listing[]>) => {
            console.log('Main Response',response)

            for(let listing of response.data){
              this.listings.push(this.imageProcessingService.createImages(listing));
            }

            console.log('data array', this.listings);

          },
        error:
          (error: HttpErrorResponse) => {
            console.log(error.error)

            if(error.status == 404){
              return;
            }
            Swal.fire({
              icon: 'error',
              title: 'An Error has Occurred while fetching data' ,
              confirmButtonColor: 'red',
              confirmButtonText: 'Ok',
              html: "Something went wrong at server level ( error Code : <b>"+ error.status+ "</b> ) , Please try again later",
            });
          }
      });
  }
  OpenCategoriesSheet(): void {
    this.bottomSheetComponent.showSheet();
  }

  redirectToCreateRoute(): void {
    this.router.navigate(['/create']);
  }

}
