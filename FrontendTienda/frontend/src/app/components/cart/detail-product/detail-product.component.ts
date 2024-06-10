import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemCart } from 'src/app/common/item-cart';
import { CartService } from 'src/app/services/cart.service';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit{
  
   id : number=0;
   name : string='';
   description : string='';
   price : number=0;
   urlImage : string='';
   quantity: number = 1; 

  
  ngOnInit(): void {
    this.getProductById();
  }

  constructor(private homeService:HomeService, private activatedRoute:ActivatedRoute, private cartService:CartService, private toastr:ToastrService){

  }

  getProductById() {
    // Suscribirse a los parámetros de la ruta activa
    this.activatedRoute.params.subscribe(
      // La variable 'p' contiene los parámetros de la ruta
      p => {
        // Extraer el 'id' de los parámetros de la ruta
        let id = p['id'];
        // Verificar si se proporcionó un 'id'
        if (id) {
          // Llamar al servicio para obtener el producto por su 'id'
          this.homeService.getProductById(id).subscribe(
            // La variable 'data' contiene la respuesta del servicio
            data => {
              // Asignar los valores del producto a las propiedades del componente
              this.id = data.id;
              this.name = data.name;
              this.description = data.description;
              this.urlImage = data.urlImage;
              this.price = data.price;
            }
          );
        }
      } 
    );
  }

  addCart(id:number){
    console.log('id product: ',id)
    console.log('name product: ',this.name)
    console.log('quantity product: ',this.quantity)
    console.log('price product: ',this.price)

    let item = new ItemCart(id, this.name, this.quantity, this.price);

    this.cartService.addItemCart(item);

    console.log('Total carrito: ')
    console.log(this.cartService.totalCart())

    this.toastr.success('Producto añadido al carrito de compras', 'Carrito compras')
  }
  

}
