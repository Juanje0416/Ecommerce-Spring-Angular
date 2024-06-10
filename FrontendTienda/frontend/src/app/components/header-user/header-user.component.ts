import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionStorageService } from 'src/app/services/session-storage.service';

declare var bootstrap: any;

@Component({
  selector: 'app-header-user',
  templateUrl: './header-user.component.html',
  styleUrls: ['./header-user.component.css']
})
export class HeaderUserComponent implements OnInit {
  authenticated: boolean = false; // Variable para controlar si el usuario está autenticado

  constructor(private sessionStorage: SessionStorageService, private router: Router) {}

  ngOnInit(): void {
    this.authenticated = this.sessionStorage.getItem('token') !== null; // Verificar si el token no es nulo
  }

  openLogoutModal(): void {
    const modalElement = document.getElementById('logoutModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  logout(): void {
    this.sessionStorage.removeItem('token');
    this.authenticated = false;
    this.router.navigate(['/user/login']);
    const modalElement = document.getElementById('logoutModal');
    const modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();
  }
}
