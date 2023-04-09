import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationCommonService {

  constructor(private router: Router) { }

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
    localStorage.setItem('token',token);
    setTimeout(() => {
      localStorage.removeItem('token');
    }, 1000 * 60 * 60 * 24);
  }

  public redirectToHome(): void{
    this.router.navigate(['/home']);
  }
}
