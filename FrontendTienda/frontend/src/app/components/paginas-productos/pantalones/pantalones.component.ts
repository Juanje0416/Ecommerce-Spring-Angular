import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-pantalones',
  templateUrl: './pantalones.component.html',
  styleUrls: ['./pantalones.component.css']
})
export class PantalonesComponent implements OnInit{

  products: Product[] = []; // Lista de productos, inicializada vacía

  constructor(private homeService: HomeService) { }

  // Método que se ejecuta al cargar el componente
  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    const categoryId = 3; 
    this.homeService.getProductsByCategoryId(categoryId).subscribe(
      data => this.products = data 
    );
  }


}

