import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TitleComponent } from './title/title.component';




@NgModule({
  declarations: [],
  exports:[TitleComponent],
  imports: [CommonModule,TitleComponent]
})
export class IndexModule { }
