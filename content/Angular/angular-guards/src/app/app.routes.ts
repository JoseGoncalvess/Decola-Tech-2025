import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProdutosComponent } from './components/produtos/produtos.component';
import { SobreComponent } from './components/sobre/sobre.component';
import { LoginComponent } from './components/login/login.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  {
    path : "",
    pathMatch :"full",
    component : HomeComponent
  },
  {
    path : "produtos",
    component : ProdutosComponent,
    canActivate : [authGuard], // aqui que uso  o guard e ele tera a logica de validação
  },
  {
    path : "sobre",
    component : SobreComponent
  },
  {
    path : "login",
    component : LoginComponent
  },
  {
    path:"**",
    redirectTo: ""
  }
];
