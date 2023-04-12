import { Injectable } from '@angular/core';
import {Listing} from "../models/listings/Listing.model";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {
  constructor(private sanitizer: DomSanitizer) { }

  public createImages(listing: Listing): Listing {
    listing.fileHandling = [];
    if (listing.assets) {
      console.log(listing.assets)

      for (let image of listing.assets) {
        const createdImageFile = new File(
          [this.dataURIToBlob(image.picByte , image.type)],
          image.name,
          { type: image.type }
        );
        console.log('from file' , createdImageFile)

          listing.fileHandling.push({
              file: createdImageFile,
              url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(createdImageFile)),
              unsafeUrl: window.URL.createObjectURL(createdImageFile)
            }
          );

        console.log('from service',listing)
      }

    }

    return listing;
  }

  private dataURIToBlob(picBytes: string , imageType: string) {
    const byteString = atob(picBytes);

    const int8Array = new Uint8Array(
      new ArrayBuffer(byteString.length)
    );

    for(let i = 0; i < byteString.length; i++){
        int8Array[i] = byteString.charCodeAt(i);
    }

    return new Blob([int8Array] , { type: imageType })
  }

}
