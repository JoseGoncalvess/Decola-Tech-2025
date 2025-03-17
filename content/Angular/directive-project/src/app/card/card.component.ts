import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-card',
  imports: [CommonModule],
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'] // Corrigido aqui
})
export class CardComponent implements OnInit {
  prdutos: String[] = [];
  meuType : String ="superuser";

  constructor() {
    this.prdutos = ["Mouse", "teclado", "Processador", "Monitor", "Placa de video"];
  }

  ngOnInit(): void {}

  insertItem(): void {
    this.prdutos.push("Good Of War Ragnarok");
    console.log(this.prdutos);

  }

  deletItem(index: number): void {
    this.prdutos.splice(index, 1);
    console.log(this.prdutos);
  }
}
