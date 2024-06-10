import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-zapatos',
  templateUrl: './zapatos.component.html',
  styleUrls: ['./zapatos.component.css']
})
export class ZapatosComponent implements OnInit{

  products: Product[] = []; // Lista de productos, inicializada vacía

  constructor(private homeService: HomeService) { }

  // Método que se ejecuta al cargar el componente
  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    const categoryId = 4; 
    this.homeService.getProductsByCategoryId(categoryId).subscribe(
      data => this.products = data 
    );
  }


}