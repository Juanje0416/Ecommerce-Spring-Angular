import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit{
  products : Product[] = [];
  
  p: number = 1;

  constructor(private productService:ProductService){

  }
  
  ngOnInit(): void {
    this.listProducts();
  }

  listProducts(){
    this.productService.getProducts().subscribe(
      data => {this.products = data
        console.log(data);//esto es para que me muestre la información en el inspeccionar
      }
    );
  }

  onImageError(event: Event) {
    const imgElement = event.target as HTMLImageElement;
    imgElement.src = 'ruta/de/la/imagen/alternativa.png'; // URL de una imagen de respaldo
  }
  

  deleteProductById(id: number) {
    swal({
      title: '¿Estás seguro?',
      text: 'Confirma si deseas eliminar el producto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminarlo',
      cancelButtonText: 'No, cancelar',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: true
    }).then((result) => {
      if (result.value) {
        this.productService.deleteProdyctById(id).subscribe(
          () => {
            this.listProducts();
            swal(
              'Producto eliminado',
              'El producto ha sido eliminado con éxito',
              'success'
            );
          }
        );
      }
    });
  }
  
}



