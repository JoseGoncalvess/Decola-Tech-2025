import { Component , Input } from '@angular/core';
import { RouterModule } from '@angular/router'; // TEM QUE SER IMPORTADA PARA PODER SER USADA


@Component({
  selector: 'app-small-card',
  imports: [RouterModule],
  templateUrl: './small-card.component.html',
  styleUrl: './small-card.component.css'
})
export class SmallCardComponent {
  @Input() title: string = "";
  @Input() imgSrc: string = "";
   @Input() id : string = "0"
  constructor(){}

}
