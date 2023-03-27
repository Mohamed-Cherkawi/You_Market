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
  countries!: [string, string][];

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
  }
  public onInputFocus(parentElementId: string): void {
    focus(parentElementId);
  }
  public onInputBlur(parentElementId: string): void {
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

  public triggerNextOrPreviousButton(): void{
      if(this.nextPrevButt === 'Next'){
        this.nextPrevButt = 'Previous';
      } else {
        this.nextPrevButt = 'Next';
      }
  }
  private getCountriesDataFromJsonObject():void {
    this.countries = Array.from(
      new Map<string, string>(
        Object.entries(countriesDataJson).map(([key, value]) => [key, value as string])
      ).entries()
    );
    this.countries[252] = ['','-- Select a country --'];
  }
}
