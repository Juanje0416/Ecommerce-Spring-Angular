import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HeaderService } from './header.service';
import { User } from '../common/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserCrudService {
  private apiUrl: string = "http://localhost:8085/api/v1/admin/usercrud";

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/${id}`, { headers: this.headerService.headers });
  }

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.apiUrl, { headers: this.headerService.headers });
  }

  deleteUserById(id: number): Observable<any> {
    return this.httpClient.delete(`${this.apiUrl}/${id}`, { headers: this.headerService.headers });
  }

  
}
