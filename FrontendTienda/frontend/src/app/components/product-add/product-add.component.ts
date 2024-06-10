import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/common/category';
import { ProductService } from 'src/app/services/product.service';
import { CategoryService } from 'src/app/services/category.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit { 
  id: number = 0;
  code: string = '';
  name: string = '';
  description: string = '';
  price: number = 0;
  urlImage: string = '';
  userId: string = '0';
  categoryId: string = '';
  selectFile!: File; // La variable no puede ser nula

  categories: Category[] = []; // Array vacío para almacenar las categorías

  constructor(
    private productService: ProductService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private categoryService: CategoryService,
    private sessionStorage: SessionStorageService
  ) {}

  addProduct() {   
    const formData = new FormData();
    formData.append('id', this.id.toString());
    formData.append('code', this.code);
    formData.append('name', this.name);
    formData.append('description', this.description);
    formData.append('price', this.price.toString());
    formData.append('image', this.selectFile);
    formData.append('urlImage', this.urlImage);
    formData.append('userId', this.userId);
    formData.append('categoryId', this.categoryId);
    
    // Enviamos los datos al backend
    this.productService.createProduct(formData).subscribe(
      data => {
        swal('Producto guardado', `El producto: ${this.name} se ha guardado correctamente`, 'success');
        this.router.navigate(['admin/product']);
      },
      error => {
        console.error(error);
        swal('Error', 'Hubo un problema al guardar el producto.', 'error');
      }
    );
  }

  getProductById() { 
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']; 
      if (id) {
        this.productService.getProductById(id).subscribe(data => {
          this.id = data.id;
          this.code = data.code;
          this.name = data.name;
          this.description = data.description;
          this.urlImage = data.urlImage;
          this.price = data.price;
          this.userId = data.userId;
          this.categoryId = data.categoryId;
        });
      }
    });
  }

  ngOnInit(): void {
    this.getCategories(); 
    this.getProductById(); 
    this.userId = this.sessionStorage.getItem('token').id;
  }

  imgTypeError = false;

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const fileType = file.type;
      const validImageTypes = ['image/jpeg', 'image/png', 'image/jpg'];
      this.imgTypeError = !validImageTypes.includes(fileType);
      if (!this.imgTypeError) {
        this.selectFile = file; // Almacena el archivo seleccionado
      }
    } else {
      this.imgTypeError = false;
    }
  }
  
  getCategories() { 
    this.categoryService.getCategoryList().subscribe(
      data => this.categories = data
    );
  }
}
