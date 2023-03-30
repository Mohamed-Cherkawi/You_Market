import {Component} from '@angular/core';

declare const showSheet: Function;
declare const hideSheet: Function;
@Component({
  selector: 'app-bottom-sheet',
  templateUrl: './bottom-sheet.component.html',
  styleUrls: ['./bottom-sheet.component.css']
})
export class BottomSheetComponent {

  constructor() {}

  showSheet(){
    showSheet();
  }
  hideSheet(){
    hideSheet();
  }
}
