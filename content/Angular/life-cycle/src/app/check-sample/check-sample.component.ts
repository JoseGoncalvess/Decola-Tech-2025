import { Component, OnInit, DoCheck, AfterContentChecked, AfterContentInit, AfterViewInit, OnDestroy } from '@angular/core';
import {CommonModule, NgIf} from '@angular/common';
@Component({
  selector: 'app-check-sample',
  imports: [CommonModule],
  templateUrl: './check-sample.component.html',
  styleUrl: './check-sample.component.css'
})
export class CheckSampleComponent implements
AfterContentChecked,
AfterContentInit,
AfterViewInit,
OnDestroy {
  ngOnDestroy(): void {

    console.log("OnDestroy");
    alert("goodbye My Friend");
  }

  counter : number = 0;

   increment():void {
    this.counter++;
  }

  decrement():void {
    this.counter--;
  }

  // cheke -> content -> view

  // Quando o primeiro conteudo é atulizado
  ngAfterContentChecked(): void {
    return console.log("ngAfterContentChecked");
  }

    // pos uma atualizaçao verifica o conteudo
  ngAfterContentInit(): void {
    return console.log("ngAfterContentInit");
  }

  // Verifica  View
  ngAfterViewInit(): void {
    return console.log("ngAfterViewInit");
  }



}
