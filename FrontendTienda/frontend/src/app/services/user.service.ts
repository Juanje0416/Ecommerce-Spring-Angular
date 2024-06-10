import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl : string = "http://localhost:8085/api/v1/users"

  constructor(private httpClient:HttpClient,private headerService:HeaderService) { }

  getUserById(id:number):Observable<User>{
    //return this.httpClient.get<User>(this.apiUrl+'/id')
    return this.httpClient.get<User>(`${this.apiUrl}/${id}`, {headers:this.headerService.headers});//forma recomendada
  }

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.apiUrl, {headers:this.headerService.headers});
  }
  
  deleteUserById(id:number):Observable<any>{
    return this.httpClient.delete(this.apiUrl+"/"+id, {headers:this.headerService.headers});
  }

  addUser(formData:any):Observable<any>{
    return this.httpClient.post<User>(this.apiUrl, formData, {headers:this.headerService.headers});
  }

}
