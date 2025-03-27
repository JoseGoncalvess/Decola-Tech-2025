import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent implements OnInit {
  constructor( private activeRoute : ActivatedRoute,
    private router: Router
  ) {
  // portifolioId: string;
    this.activeRoute.params.subscribe(res => console.log(res));

// PEGANDO PARAMETRO DAS ROTAS FILHAA
this.activeRoute.firstChild?.params.subscribe(res => console.log(res));

      // portifolioId = name:anny&idade:23
    this.activeRoute.queryParams.subscribe(res => console.log(res))
  }
  ngOnInit(): void {
   setInterval(()=>{
    this.router. navigate(["/"])
   }, 50)
  }

}
