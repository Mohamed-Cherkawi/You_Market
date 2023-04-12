import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationCommonService {

  constructor(private router: Router,
              private jwtHelper: JwtHelperService) { }

  public handleFormButton(): void {
    const button = document.querySelector("button");
    button!.disabled = true;
    button!.style.opacity = "0.5";

    setTimeout(function() {
      button!.disabled = false;
      button!.style.opacity = "1";
    }, 1000);
  }

  public handleUserToken(token: string): void {
    const decodedToken = this.jwtHelper.decodeToken(token);

    localStorage.setItem('token',token);
    localStorage.setItem('userReference' , decodedToken.userReference);

    setTimeout(() => {
      localStorage.removeItem('token');
    }, 1000 * 60 * 60 * 24);

    this.redirectToHome();
  }

  public redirectToHome(): void{
    this.router.navigate(['/home']);
  }
}
