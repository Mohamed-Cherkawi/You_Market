import {Component, ViewChild} from '@angular/core';
import {BottomSheetComponent} from "../../layouts/bottom-sheet/bottom-sheet.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{
  @ViewChild(BottomSheetComponent) bottomSheetComponent!: BottomSheetComponent ;

  links = [
    { svgIconName: 'store' , linkName : 'Browse all' , imgAlt : 'Store Icon' },
    { svgIconName: 'bell' , linkName : 'Notifications' , imgAlt : 'Notification Icon' },
    { svgIconName: 'inbox' , linkName : 'Inbox' , imgAlt : 'Inbox Icon' },
    { svgIconName: 'bag-shopping' , linkName : 'Buying' , imgAlt : 'Shopping Bag Icon' },
    { svgIconName: 'tag' , linkName : 'Selling' , imgAlt : 'Tag Icon' },
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


  constructor(private router: Router) {}

  OpenCategoriesSheet(): void {
    this.bottomSheetComponent.showSheet();
  }

  redirectToCreateRoute(): void {
    this.router.navigate(['/create']);
  }
}
