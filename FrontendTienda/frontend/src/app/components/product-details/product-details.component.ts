import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';
import swal from 'sweetalert2';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit{

  id : Number = 0;
  code : string = '001';
  name : string = '';
  description : string = '';
  price : number = 0;
  urlImage : string = '';
  userId : string = '1';
  categoryId : string = '5';

  constructor(private productService: ProductService,private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.getProductById();
  }

  getProductById(){ //recibimos el id del producto para poderlo obtener
    this.activatedRoute.params.subscribe(
      product => {
        let id = product['id']; //declaro una variable y recibo el parametro que viene con ese nombre con un array
        if(id){
          console.log('el valor de la variable id es: '+id);
          this.productService.getProductById(id).subscribe(
            data => {
              this.id = data.id;
              this.code = data.code;
              this.name = data.name;
              this.description = data.description;
              this.urlImage = data.urlImage;
              this.price = data.price;
              this.userId = data.userId;
              this.categoryId = data.categoryId
              //con estos vemos los atributos en la plantilla ya que previuamente los tenía mapeados 
              swal(`Detalles del producto: ${this.name}`, '', 'success');
            }
          );
        }

      }
    );
  }
 
}
