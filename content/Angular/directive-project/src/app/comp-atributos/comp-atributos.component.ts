import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms"
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-comp-atributos',
  imports: [CommonModule, FormsModule],
  templateUrl: './comp-atributos.component.html',
  styleUrl: './comp-atributos.component.css'
})
export class CompAtributosComponent {
  style: String = "enable";
  fundo: String = "purple";
  letras: String = "white";
  isAnaleBlock: boolean = true;

  item: string = "";

  lista: string[] = []


  constructor() {
    this.lista.push(this.item);
  }

  mudou() {
    if (this.style == "desable") {
      this.style = "enable";
    } else {
      this.style = "desable";
    }
  }

  colorir() {
    if (this.fundo == "purple") {
      this.fundo = "green";
      this.letras = "black";
    } else {
      this.fundo = "purple";
      this.letras = "white";
    }
  }

  adicionarLista(): void {
    if (this.item != "") {
      this.lista.push(this.item);
    }
  }



}

