import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputComponent } from './input/input.component';
import{ListViewComponent} from "./list-view/list-view.component"



@NgModule({
  exports:[InputComponent, ListViewComponent],
  declarations: [],
  imports: [CommonModule, InputComponent,ListViewComponent]
})
export class ListaModule { }
