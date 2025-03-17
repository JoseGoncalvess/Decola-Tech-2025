import { Component, OnInit, OnChanges, Input } from '@angular/core';

@Component({
  selector: 'app-title',
  imports: [],
  templateUrl: './title.component.html',
  styleUrl: './title.component.css'
})

export class TitleComponent implements OnInit, OnChanges {
 @Input() nome : string = "";

constructor(){
  console.log("constructor");
}
// ONDE TUDO COMEÇA NO COMPONENTE
  ngOnInit(): void {
    console.log("ngOnInit");
  }

  // QUANDO O COMPONENTE È ALTERADO
   ngOnChanges() : void{
    console.log("Onchanges");

   }
}
