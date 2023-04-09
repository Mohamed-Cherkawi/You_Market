import * as categoriesJsonData from '../../../utils/json/item-listing-categories.json';
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ItemListingCategory} from "../../../models/listings/item-listing-category.model";
import {DomSanitizer, SafeStyle} from "@angular/platform-browser";
import {FileHandle} from "../../../models/other/file-handle.model";
import {ItemListing} from "../../../models/listings/item/item-listing.model";
import {ListingType} from "../../../enums/listing-type.enum";
import {ItemListingService} from "../../../services/item-listing.service";
import {ResponseEntity} from "../../../utils/response-entity.model";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit{
  @ViewChild('ItemListingForm') ItemListingForm!: ElementRef;
  meetupPreferences = [
    { iconName : 'public' , title : 'Public meetup' , resume : 'Meet at a public location.' , alt : 'P Icon' , formControlName: 'isPublicMeetup'},
    { iconName : 'door' , title : 'Door pickup' , resume : 'Buyer picks up at your door.' , alt : 'Door Icon' , formControlName: 'isDoorPickup' },
    { iconName : 'door' , title : 'Door dropoff' , resume : 'you drop off at buyers \'s door.' , alt : 'Door Icon' , formControlName: 'isDoorDropoff'},
  ];
  listingConditions = [
    { preview: 'New' , value : 'NEW' },
    { preview: 'Used - Like New' , value : 'USED_LIKE_NEW' },
    { preview: 'Used - Good' , value : 'USED_GOOD' },
    { preview: 'Used - Fair' , value : 'USED_FAIR' },
  ];
  itemForm! : FormGroup;
  categories!: ItemListingCategory[];
  filesArrayHolder: FileHandle[] = [];
  currentActiveFilesArrayIndex: number = 0;

  constructor(private formBuilder: FormBuilder ,
              private itemListingService: ItemListingService,
              private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.itemForm = this.formBuilder.group({
        title: ['', [Validators.required , Validators.maxLength(99)]],
        price: ['', [Validators.required]] ,
        category: ['' , [Validators.required]],
        condition: ['' , [Validators.required]],
        brand: ['' , [Validators.maxLength(99)]],
        description: ['' , [Validators.maxLength(255)]],
        sku: ['' , [Validators.maxLength(99)]],
        isPublicMeetup: [false],
        isDoorPickup: [false],
        isDoorDropoff: [false]
      });

    this.getCategoriesDataFromJsonObject();
  }

  onSubmit() {
    console.log(this.filesArrayHolder);
    console.log(this.itemForm.value)

    if(!this.itemForm.valid || this.filesArrayHolder.length == 0){
      // Swal.fire('Any fool can use a computer').then(r => console.log(r));
      return;
    }

    this.itemListingService.createItemListing(
      this.prepareFormData(
        this.prepareItemListingObject()
      ))
      .subscribe(
        {
          next:
            (response: ResponseEntity<ItemListing>) => {
              console.log(response);
            },
          error:
            (error : HttpErrorResponse) => {
              console.log(error.error)
            }
        });
  }

  private prepareItemListingObject(): ItemListing {
    return  {
      fileHandling: this.filesArrayHolder,
      ownerReference: 'HDHJD38638363JDH',
      price: this.itemForm.get('price')?.value,
      description: this.itemForm.get('description')?.value,
      listingType: ListingType.ITEM,
      title: this.itemForm.get('title')?.value,
      location: {
        title: 'Marrakech',
        description: 'For test purposes'
      },
      publicMeetup: this.itemForm.get('isPublicMeetup')?.value,
      doorDropOff: this.itemForm.get('isDoorDropoff')?.value,
      doorPickup: this.itemForm.get('isDoorPickup')?.value,
      properties: {
        category: this.itemForm.get('category')?.value,
        condition: this.itemForm.get('condition')?.value,
        brand: this.itemForm.get('brand')?.value,
        sku: this.itemForm.get('sku')?.value
      }
    };
  }
  private prepareFormData(listing: ItemListing): FormData {
    const formData = new FormData();

    formData.append(
      'item',
      new Blob([JSON.stringify(listing)] , { type: 'application/json' })
    );

    listing.fileHandling.forEach((file: FileHandle) => {
        formData.append(
          'imagesFiles',
          file.file,
          file.file.name
        );
    });

    return formData;
  }

  onFileSelected(event: any) {

    for (let file of event.target.files) {
          this.filesArrayHolder.push({
            file: file,
            url: this.sanitizer.bypassSecurityTrustUrl(
              window.URL.createObjectURL(file)
            )
          });
      }

    console.log(this.filesArrayHolder[0].url.toString());
  }

  getBackgroundImageStyle(): SafeStyle {
    return { 'background-image': this.sanitizer.bypassSecurityTrustStyle(`url(${this.filesArrayHolder[0].url})`) };
  }

  private getCategoriesDataFromJsonObject():void {
    this.categories = Array.from(
      Object.values(categoriesJsonData).map((value) => value)
    );

    this.categories.splice(6,2);

  }

  public removeImage(fileIndex: number){
      this.filesArrayHolder.splice(fileIndex,1);
  }

  public fileDropped(files: FileHandle[]){
    for(let file of files){
      this.filesArrayHolder.push(file);
    }
  }

  public incrementOrDecrementFilesArrayIndex(willIncrement: boolean): void {

    if(willIncrement){
      if(this.currentActiveFilesArrayIndex == this.filesArrayHolder.length - 1){
        this.currentActiveFilesArrayIndex = 0;
      } else {
        this.currentActiveFilesArrayIndex++;
      }

    }else {
      if(this.currentActiveFilesArrayIndex == 0 ){
        this.currentActiveFilesArrayIndex = this.filesArrayHolder.length - 1;
      } else {
        this.currentActiveFilesArrayIndex--;
      }
    }
  }
}
