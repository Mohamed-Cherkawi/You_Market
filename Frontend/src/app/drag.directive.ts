import {Directive, EventEmitter, HostBinding, HostListener, Output} from '@angular/core';
import {FileHandle} from "./models/other/file-handle.model";
import {DomSanitizer} from "@angular/platform-browser";

@Directive({
  selector: '[fileDrag]'
})
export class DragDirective {
  filesHandle: FileHandle[] = [];

  @Output() fileEventEmitter: EventEmitter<FileHandle[]> = new EventEmitter<FileHandle[]>();
  @HostBinding("style.border") private border = '4px dashed grey';
  constructor(private sanitizer: DomSanitizer) { }

  @HostListener("dragover", ["$event"])
  public onDragOver(evt: DragEvent){
    evt.preventDefault();
    evt.stopPropagation();
    this.border = '4px dashed #32be8f';
  }
  @HostListener("dragleave", ["$event"])
  public onDragLeave(evt: DragEvent){
    evt.preventDefault();
    evt.stopPropagation();
    this.border = "4px dashed grey";

  }
  @HostListener("drop", ["$event"])
  public onDrop(evt: DragEvent){
    evt.preventDefault();
    evt.stopPropagation();
    this.border = "4px dashed grey";

   const filesList: FileList = evt.dataTransfer!.files;

    console.log(filesList);

    for (let file of Array.from(filesList)) {
      console.log(file)
      this.filesHandle.push({
        file: file,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        )
      });
    }

   this.fileEventEmitter.emit(this.filesHandle);

    this.filesHandle = [];

  }

}
