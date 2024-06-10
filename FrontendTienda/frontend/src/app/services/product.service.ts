import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl : string = "http://localhost:8085/api/v1/admin/products";

  constructor(private httpClient:HttpClient,private headerService:HeaderService) { }

  //nos suscribimos con observable para que esté mirando el flujo y pueda retornarme los productos
  //este metodo se conecta a mi api rest y utilizando un obj de tipo httpClient hago un get indicando que quiero traer una lista de productos a mi url
  getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl, {headers:this.headerService.headers});
  }

  createProduct(formData:any):Observable<any>{
    return this.httpClient.post<Product>(this.apiUrl, formData, {headers:this.headerService.headers});
  }

  deleteProdyctById(id:number):Observable<any>{
    return this.httpClient.delete(this.apiUrl+"/"+id, {headers:this.headerService.headers});
  }

  getProductById(id:number):Observable<Product>{
    return this.httpClient.get<Product>(this.apiUrl+"/"+id, {headers:this.headerService.headers});
  }

  getProductsByCategoryId(categoryId: number): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${this.apiUrl}/category/${categoryId}`);
}

  
}
