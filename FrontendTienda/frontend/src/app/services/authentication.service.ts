import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';
import { Userdto } from '../common/userdto';
import { Jwtclient } from '../common/jwtclient';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private apiUrl : String = 'http://localhost:8085/api/v1/security'

  constructor(private httpClient : HttpClient) { }

  //metodo para registrar
  register(user : User):Observable<User>{
    return this.httpClient.post<User>(this.apiUrl+"/register",user)
  }

  //metodo login
  //para este emtodo lo que debo recibir es un objeto de tipo userDTO
  login(userDto : Userdto):Observable<Jwtclient>{
    return this.httpClient.post<Jwtclient>(this.apiUrl+"/login",userDto)
  }

}
