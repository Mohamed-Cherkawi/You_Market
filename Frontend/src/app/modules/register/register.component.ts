import * as countriesDataJson from '../../utils/json/countries-apis.json';

import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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

  constructor(
    private formBuilder: FormBuilder,) { }

  ngOnInit(): void {
    this.getCountriesDataFromJsonObject();

    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      phone: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      country: ['',[Validators.required]]
    });
    console.log(this.countries)
  }

  public onRegister(): void{
    console.log(this.registerForm.value.phone);
    console.log(this.registerForm.value.name);
    console.log(this.registerForm.value.country);
    console.log(this.registerForm.value.username);
    console.log(this.registerForm.value.password);

    this.validateFormInputs();
  }
  public onInputFocus(parentElementId: string): void {
    focus(parentElementId);
  }
  public onInputBlur(parentElementId: string , formNumber: number): void {
    switch (formNumber){
      case 1 :
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
        break;
      case 2 :
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
  private validateFormInputs(): void {
    if(! this.registerForm.value.name
      || ! this.registerForm.value.phone
      || ! this.registerForm.value.country
      || ! this.registerForm.value.username
      || ! this.registerForm.value.password){
      this.credentialsError = true;
    }
  }
  private getCountriesDataFromJsonObject():void {
    this.countries = Array.from(
        Object.values(countriesDataJson).map((value) => value as string)
    );
    this.countries[252] = '-- Select a country --';
  }
}
