import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/common/category';
import { CategoryService } from 'src/app/services/category.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit{
  
  categories: Category [] = [];
  p: number = 1;
  
  constructor(private categoryService:CategoryService){}

  ngOnInit(): void {
    this.listCategories();
  }

  listCategories() {
    this.categoryService.getCategoryList().subscribe(
      data => this.categories = data
    );
  }

  deleteCategoryById(id:number){
    swal({
      title: '¿Estás seguro?',
      text: 'Confirma si deseas eliminar la categoría',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminarla',
      cancelButtonText: 'No, cancelar',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: true
    }).then((result) => {
      if (result.value) {
        this.categoryService.deleteCategoryById(id).subscribe(
          () => {
            this.listCategories();
            swal(
              'Cateforía eliminada',
              'La categoría ha sido eliminada con éxito',
              'success'
            );
          }
        );
      }
    });
  }
  
  

}
