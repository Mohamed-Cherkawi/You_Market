import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResponseEntity} from "../utils/response-entity.model";
import {ItemListing} from "../models/listings/item/item-listing.model";

@Injectable({
  providedIn: 'root'
})
export class ItemListingService {
  private restPath: string = environment.apisBaseUrl + "/api/listing/item/";
  constructor(private http: HttpClient) {}

  public createItemListing(request: FormData): Observable<ResponseEntity<ItemListing>> {
    return this.http.post<ResponseEntity<ItemListing>>(`${this.restPath}creating`,request);
  }
}
