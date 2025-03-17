import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CardComponent } from "./card/card.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CardComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] // Corrigido aqui
})
export class AppComponent {
  title = 'directive-project';
  isAlive: boolean = true;
}
