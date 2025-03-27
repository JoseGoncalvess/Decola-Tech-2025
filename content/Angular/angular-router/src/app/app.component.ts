import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { IndexModule } from "./pages/index/index.module";
import { PortifolioModule } from "./pages/portifolio/portifolio.module";
import { MenuComponent } from "./shared/menu/menu.component";


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, IndexModule, PortifolioModule, MenuComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-router';
}
