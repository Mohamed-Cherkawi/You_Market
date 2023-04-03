import * as categoriesJsonData from '../../../utils/json/item-listing-categories.json';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ItemListingCategory} from "../../../models/listings/item-listing-category.model";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit{

  itemForm! : FormGroup;
  categories!: ItemListingCategory[];
  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.itemForm = this.formBuilder.group({
        title: ['', [Validators.required , Validators.maxLength(99)]],
        price: ['', [Validators.required]] ,
        category: ['' , [Validators.required]],
        description: ['' , [Validators.required , Validators.maxLength(255)]],
      });

    this.getCategoriesDataFromJsonObject();
  }

  private getCategoriesDataFromJsonObject():void {
    this.categories = Array.from(
      Object.values(categoriesJsonData).map((value) => value)
    );

    console.log(this.categories);
  }

}
