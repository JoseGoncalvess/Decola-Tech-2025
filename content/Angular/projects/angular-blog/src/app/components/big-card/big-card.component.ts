import { Component , Input} from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-big-card',
  imports: [RouterModule],
  templateUrl: './big-card.component.html',
  styleUrl: './big-card.component.css'
})
export class BigCardComponent {
  @Input() title: string = "";
  @Input() imgSrc: string = "";
  @Input() description : string = ""
  @Input() id : string = "0"
  constructor(){}
}
