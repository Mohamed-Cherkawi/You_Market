import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor() {}

  // Auto called by Angular lifecycle for each request made
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if(!request.url.toString().includes("authenticate")){
      // Get the token from local storage
      const token = localStorage.getItem('token');

      console.log(token)
      let clone = request.clone({headers:request.headers.set("Authorization", `Bearer ${token}`)});
      console.log(clone)
      //let headers=new HttpHeaders().append('Authorization',`Bearer ${token}`)
      return next.handle(clone);
    }
    return next.handle(request);
  }
}
