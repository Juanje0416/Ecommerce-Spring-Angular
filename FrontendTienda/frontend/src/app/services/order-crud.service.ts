import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../common/order';
import { HttpClient } from '@angular/common/http';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class OrderCrudService {

  private apiUrl: string = "http://localhost:8085/api/v1/admin/ordercrud"; // Define la URL base de la API para las órdenes
  private update: string = "update/state/order"; // Defino el endpoint para actualizar el estado de la orden


  constructor(private httpClient: HttpClient,private headerService:HeaderService) { } // Constructor del servicio, recibo el servicio HttpClient mediante inyección de dependencias

  // Método para crear una nueva orden
  createOrder(order: Order): Observable<Order> {
    // Realiza una solicitud POST a la API para crear una nueva orden
    return this.httpClient.post<Order>(this.apiUrl, order, {headers:this.headerService.headers});
  }

  getOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.apiUrl, {headers:this.headerService.headers});
  }
  
  // Método para actualizar el estado de una orden existente
  updateOrder(formData: any): Observable<any> {
    // Realiza una solicitud POST a la API para actualizar el estado de la orden
    return this.httpClient.post(`${this.apiUrl}/${this.update}`, formData, {headers:this.headerService.headers});
}


  // Método para obtener todas las órdenes asociadas a un usuario específico
  getOrderByUser(userId: number): Observable<Order[]> {
    // Realiza una solicitud GET a la API para obtener todas las órdenes de un usuario
    return this.httpClient.get<Order[]>(`${this.apiUrl}/by-user/${userId}`, {headers:this.headerService.headers});
  }

  // Método para obtener una orden específica por su ID
  getOrderByid(orderId: number): Observable<Order> {
    // Realiza una solicitud GET a la API para obtener una orden por su ID
    return this.httpClient.get<Order>(`${this.apiUrl}/${orderId}`, {headers:this.headerService.headers});
  }


}