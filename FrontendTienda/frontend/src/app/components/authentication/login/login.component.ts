import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Userdto } from 'src/app/common/userdto';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';
  loginFailed: boolean = false; // Variable para indicar si el inicio de sesión falló

  ngOnInit(): void {
    
  }

  constructor(private authentication: AuthenticationService,
    private sessionStorage: SessionStorageService,
    private router: Router
  ) {}


  login() {
    let userDto = new Userdto(this.username, this.password);

    this.authentication.login(userDto).subscribe(
      token => {
        console.log(token);
        this.sessionStorage.setItem('token', token); // Mantengo el id del usuario y el token con ese servicio que guarda información
        if (token.type == 'ADMIN') {
          this.router.navigate(['/admin/product']); // Redirecciono dependiendo del tipo de usuario que sea 
        } else {
          this.router.navigate(['/']);
        }
      },
      error => {
        console.error('Error en inicio de sesión:', error);
        this.loginFailed = true; // Establecer la variable loginFailed en true si el inicio de sesión falla
      }
    );

    console.log(userDto);
  }
}
