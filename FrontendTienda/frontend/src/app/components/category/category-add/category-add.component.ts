import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/common/category';
import { CategoryService } from 'src/app/services/category.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css']
})
export class CategoryAddComponent implements OnInit{

  id : number = 0;
  name : String = "";

  constructor(private categoryService:CategoryService, private router:Router, private activatedRoute:ActivatedRoute ){}
  
  ngOnInit(): void {
    this.getCategoryById();
  }

  addCategory() {
    console.log(this.name);
    let category = new Category(this.id, this.name);
    this.categoryService.createCategory(category).subscribe(
        () => {
            swal('Categoría guardada', `La categoría: ${this.name} se ha guardado correctamente`, 'success');
            this.router.navigate(['/admin/category']);
        },
        error => {
            // Maneja el error aquí si es necesario
            console.error('Error al guardar la categoría:', error);
        }
    );
}

  getCategoryById(){
    this.activatedRoute.params.subscribe(
      category => {
        let id = category['id']; //defino variable id, lo que viene en el array y coincide con el nombre id lo ponga en mi variable
        if(id){
          console.log("Valor de la vraibale id: "+id)
          this.categoryService.getCategoryById(id).subscribe( //me suscribo para obtener el resultado
            data => {
              this.id = data.id;
              this.name = data.name
            }
          )
        }
      }
    )
  }


}

