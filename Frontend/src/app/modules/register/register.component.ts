import * as countriesDataJson from '../../utils/json/countries-apis.json';

import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegisterRequest} from "../../models/auth/register-request.model";
import {AuthenticationService} from "../../services/authentication.service";
import {ResponseEntity} from "../../utils/response-entity.model";
import {AuthenticationResponse} from "../../models/auth/authentication-response.model";
import {HttpErrorResponse} from "@angular/common/http";
import {AuthenticationCommonService} from "../../services/authentication-common.service";

declare const focus: Function;
declare const blur: Function;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  hide: boolean = false;
  nextPrevButt: string = 'Next';
  credentialsError: boolean = false;
  registerForm!: FormGroup;
  countries!: string[];
  registerRequest!: RegisterRequest;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthenticationService,
    private commonAuthService: AuthenticationCommonService) { }

  ngOnInit(): void {
    this.getCountriesDataFromJsonObject();

    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      phone: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      country: ['',[Validators.required]]
    });
  }

  public onRegister(): void{

    if(! this.registerForm.valid){
        this.credentialsError = true;
        return;
    }

    this.commonAuthService.handleFormButton();

    this.registerRequest = {
      name : this.registerForm.value.name,
      phone : this.registerForm.value.phone,
      address : {
        title : this.registerForm.value.country,
      },
      username : this.registerForm.value.username,
      password : this.registerForm.value.password,
    }

    this.authService.register(this.registerRequest)
      .subscribe(
      {
        next:
          (response: ResponseEntity<AuthenticationResponse>) => {
            console.log(response)

            this.commonAuthService.handleUserToken(response.data.token);
          },
        error:
        (error : HttpErrorResponse) => {
          this.credentialsError = true;
            console.log(error.error);
        }
      });
  }
  public onInputFocus(parentElementId: string): void {
    focus(parentElementId);
  }
  public onInputBlur(parentElementId: string , formNumber: number): void {
    switch (formNumber){
      case 1 :
        this.onBlurredFirstForm(parentElementId);
        break;
      case 2 :
        this.onBlurredSecondForm(parentElementId);
    }
  }
  public triggerNextOrPreviousButton(): void{
    const form1 = document.getElementById('form-1');
    const form2 = document.getElementById('form-2');

      if(this.nextPrevButt === 'Next'){
        form1!.style.display = 'none';
        form2!.style.display = 'block';
        this.nextPrevButt = 'Previous';
      } else {
        form2!.style.display = 'none';
        form1!.style.display = 'block';
        this.nextPrevButt = 'Next';
      }
  }
  private onBlurredFirstForm(parentElementId: string): void {
    if(parentElementId == "fullNameField"){

      if(this.registerForm.value.name){
        return;
      }
    }else {
      if(this.registerForm.value.phone){
        return;
      }
    }
    blur(parentElementId);

    if(!this.registerForm.value.name && !this.registerForm.value.phone){
      this.credentialsError = false;
    }
  }
  private onBlurredSecondForm(parentElementId: string): void {
    if(parentElementId == "usernameField"){

      if(this.registerForm.value.username){
        return;
      }
    }else {
      if(this.registerForm.value.password){
        return;
      }
    }
    blur(parentElementId);

    if(!this.registerForm.value.username && !this.registerForm.value.password){
      this.credentialsError = false;
    }
  }
  private getCountriesDataFromJsonObject():void {
    this.countries = Array.from(
        Object.values(countriesDataJson).map((value) => value)
    );
    this.countries.sort();
    this.countries.splice(251,1);
  }
}
