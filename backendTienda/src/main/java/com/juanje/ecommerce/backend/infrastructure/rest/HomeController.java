package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.application.ProductService;
import com.juanje.ecommerce.backend.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//creo este endpoint ya que sino en el securityconfig restrinjo a todos los que no sean usuarios autenticados de ver los productos
//la solución es crear este endpoint que sea capaz de traerme todos mis productos y que sea acceciseble para todos
@RestController
@RequestMapping("/api/v1/home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Iterable<Product>> findByCategoryId(@PathVariable Integer categoryId){
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }

}
