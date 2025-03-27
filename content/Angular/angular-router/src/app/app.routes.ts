import { Routes } from '@angular/router';
import { TitleComponent } from './pages/index/title/title.component';
import { CardComponent } from './pages/portifolio/card/card.component';

export const routes: Routes = [
  {
    path:"",
    component:TitleComponent,
    pathMatch:"full" //patMatch:"full" DEFINE SE MINHA URL TERA OU NÂO OS CARACTER DO PATH

  },
  {
    path:"portifol",
    component:CardComponent,
    pathMatch:"full" // pathMatch:"prefix" // DEFINE SE MINHA URL TERA OU NÂO OS CARACTER DO PATH
  },
// A depender da ROTAPRECISA SE USA RO FULL para força que a url tenha o path completo


// ROTA CORINGA
// {
//   path:"**",
//   redirectTo:""// AQUIPASSO A ROUTA PARA SER REDIRECIONADO NO CASO ESTOU PASSANDO A RAIZ
// },

// ROTAS COM PARAMETROS

{
  path:"portifolioid/:id", // ELE VAI CRIAR UMA VARIAVEL ID
  component:CardComponent,
  pathMatch:"prefix"
},

// ROTAS FIHAS
{
  path:"portifolio",
  component:CardComponent,
  children:[
    {path:":id", component: CardComponent} // AQUI PODERIA TER MULTIPLAS ROTAS QUE SERIA FILHO DO PORTIFOLIO
  ]

}
];
