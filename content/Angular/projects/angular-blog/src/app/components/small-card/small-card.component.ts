import { Component , Input } from '@angular/core';

@Component({
  selector: 'app-small-card',
  imports: [],
  templateUrl: './small-card.component.html',
  styleUrl: './small-card.component.css'
})
export class SmallCardComponent {
  @Input() title: string = "";
  @Input() imgSrc: string = "";
  constructor(){}

}
