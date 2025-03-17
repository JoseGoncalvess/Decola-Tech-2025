import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TitleComponent } from "./title/title.component";
import { CheckSampleComponent } from "./check-sample/check-sample.component";
import {CommonModule} from '@angular/common';



@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TitleComponent,CheckSampleComponent,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'life-cycle';
  isAliveCheckSample: boolean = true;

  onDestroiElemnt():void{
    this.isAliveCheckSample = !this.isAliveCheckSample;
  }
}
