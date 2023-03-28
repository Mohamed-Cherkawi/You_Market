import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {AuthenticationRequest} from "../models/auth/authentication-request.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ResponseEntity} from "../utils/response-entity.model";
import {AuthenticationResponse} from "../models/auth/authentication-response.model";
import {RegisterRequest} from "../models/auth/register-request.model";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private restPath: string = environment.apisBaseUrl + "/api/authenticate/";
  constructor(private http: HttpClient) {}

  public  register(request: RegisterRequest): Observable<ResponseEntity<AuthenticationResponse>> {
    return this.http.post<ResponseEntity<AuthenticationResponse>>(`${this.restPath}register`,request);
  }
  public  authenticate(request: AuthenticationRequest): Observable<ResponseEntity<AuthenticationResponse>> {
    return this.http.post<ResponseEntity<AuthenticationResponse>>(`${this.restPath}login`, request);
  }
}
