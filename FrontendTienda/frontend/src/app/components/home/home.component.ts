import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  products: Product[] = []; // Lista de productos, inicializada vacía

  constructor(private homeService: HomeService) { }

  // Método que se ejecuta al cargar el componente
  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    const categoryId = 7; 
    this.homeService.getProductsByCategoryId(categoryId).subscribe(
      data => this.products = data 
    );
  }


}
