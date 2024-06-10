package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.application.CategoryService;
import com.juanje.ecommerce.backend.domain.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //con esta anotación lo que consigo es que la clase se exponga con sus métodos
@RequestMapping("/api/v1/admin/categories") //url especifica para este recurso que estoy exponiendo
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping //este metodo también sirve para actualizar, la clase ResponseEntity nos permite tener mas flexibilidad a la ora de darnos información
    public ResponseEntity<Category> save(@RequestBody Category category){ //lo que hace la anotación @RequestBody es permitir el mapeo desde el formato JSON a Java
        log.info("Nombre categoría: {}",category.getName());
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    //con este metodo obtenemos todas las categorías que tengo en mi BBDD
    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll(){
        return ResponseEntity.ok().body(categoryService.findAll()); // Corregido aquí
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.findById(id)); // Corregido aquí
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
