import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from './session-storage.service';
import { Jwtclient } from '../common/jwtclient';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  //centralizado mi ts

  private token : Jwtclient 
  public headers : HttpHeaders = new HttpHeaders
  
  constructor(private sessionStorage: SessionStorageService) { 
    this.token = this.sessionStorage.getItem('token');
    if (this.token != null) { 
      console.log(this.token.token);
      this.headers = new HttpHeaders({
        'Authorization': `${this.token.token}` 
      });
    }
  }
}