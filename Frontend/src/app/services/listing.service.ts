import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ResponseEntity} from "../utils/response-entity.model";
import {Listing} from "../models/listings/Listing.model";

@Injectable({
  providedIn: 'root'
})
export class ListingService {
  private restPath: string = environment.apisBaseUrl + "/api/listing/";
  constructor(private http: HttpClient) { }

  public getAllListings(): Observable<ResponseEntity<Listing[]>> {
    return this.http.get<ResponseEntity<Listing[]>>(`${this.restPath}fetching/all`);
  }
}
