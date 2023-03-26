import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {AuthenticationRequest} from "../../models/auth/authentication-request.model";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ResponseEntity} from "../../utils/response-entity.model";
import {AuthenticationResponse} from "../../models/auth/authentication-response.model";

declare const focus: Function;
declare const blur: Function;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  hide: boolean = false;
  credentialsError: boolean = false;
  loginForm!: FormGroup;
  authenticationRequest!: AuthenticationRequest;


  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  public onLogin(){
    localStorage.removeItem('token');

    if (!this.loginForm.valid)
      return;

    this.handleFormButton();


    this.authenticationRequest = {
      username : this.loginForm.value.username,
      password : this.loginForm.value.password
    };

    this.authService.authenticate(this.authenticationRequest)
      .subscribe(
        {
          next:
            (response: ResponseEntity<AuthenticationResponse>) => {
              this.credentialsError = false;
              console.log(response);

              const token = response.data.token;
              localStorage.setItem('token',token);

              this.redirectToHome();
            },
          error:
            (error : HttpErrorResponse) => {
              this.credentialsError = true;
              console.log(error)
            }
        });

  }
  private redirectToHome(): void{
      this.router.navigate(['/home']);
  }
  private handleFormButton(): void {
    const button = document.querySelector("button");
    button!.disabled = true;
    button!.style.opacity = "0.5";

    setTimeout(function() {
      button!.disabled = false;
      button!.style.opacity = "1";
    }, 1000);
  }

  public onInputFocus(parentElementId: string): void {
    focus(parentElementId);
  }
  public onInputBlur(parentElementId: string): void {
    if(parentElementId == "usernameField"){

        if(this.loginForm.value.username.length != 0){
            return;
        }
    }else {
      if(this.loginForm.value.password.length != 0){
        return;
      }
    }
    blur(parentElementId);

    if(this.loginForm.value.password.length == 0 && this.loginForm.value.username.length == 0){
        this.credentialsError = false;
    }
  }
}
