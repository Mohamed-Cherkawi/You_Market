import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {AuthenticationRequest} from "../../models/auth/authentication-request.model";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ResponseEntity} from "../../utils/response-entity.model";
import {AuthenticationResponse} from "../../models/auth/authentication-response.model";
import {AuthenticationCommonService} from "../../services/authentication-common.service";

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
              private authService: AuthenticationService,
              private commonAuthService: AuthenticationCommonService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  public onLogin(){

    if (!this.loginForm.valid)
      return;

    this.commonAuthService.handleFormButton();


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

              this.commonAuthService.handleUserToken(response.data.token);
            },
          error:
            (error : HttpErrorResponse) => {
              this.credentialsError = true;
              console.log(error.error)
            }
        });

  }

  public onInputFocus(parentElementId: string): void {
    focus(parentElementId);
  }
  public onInputBlur(parentElementId: string): void {
    if(parentElementId == "usernameField"){

        if(this.loginForm.value.username){
            return;
        }
    }else {
      if(this.loginForm.value.password){
        return;
      }
    }
    blur(parentElementId);

    if(!this.loginForm.value.password && !this.loginForm.value.username){
        this.credentialsError = false;
    }
  }
}
