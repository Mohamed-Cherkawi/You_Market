import { Component } from '@angular/core';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent  {
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

  constructor() { }


}
