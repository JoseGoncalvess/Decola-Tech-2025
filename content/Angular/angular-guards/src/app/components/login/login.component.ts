import { Component } from '@angular/core';
import { CommonModule } from "@angular/common";
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  imports: [CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
/**
 *
 */
constructor( private loginService: LoginService) {

}
  logar(){
    this.loginService.doLogin()
  }

  deslogar(){
    this.loginService.doLogout()
  }

}
