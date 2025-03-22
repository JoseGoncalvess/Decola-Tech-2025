import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ListaModule } from '../app/lista/lista.module';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ListaModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-models';
}
