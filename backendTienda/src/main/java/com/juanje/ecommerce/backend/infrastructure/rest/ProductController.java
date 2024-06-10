package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.application.ProductService;
import com.juanje.ecommerce.backend.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/admin/products")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestParam("id") Integer id,
                                        @RequestParam("code") String code,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String  description,
                                        @RequestParam("price")BigDecimal price,
                                        @RequestParam("urlImage")String urlImage,
                                        @RequestParam("userId")Integer userId,
                                        @RequestParam("categoryId")Integer categoryId,
                                        @RequestParam(value = "image", required = false)MultipartFile multipartFile//el false es para decirle que puede o no llevar una imagen, en caso de que no se introduzca se pondrá la por defecto

    ) throws IOException {
        //Creo un nuevo objeto Product
        Product product = new Product();

        //establezco los atributos del producto con sus valores
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        product.setUserId(userId);
        product.setUrlImage(urlImage);

        //esto me permite hacer un seguimiento de los datos más exact0
        log.info("NOmbre producto: {}", product.getName());

        return  new ResponseEntity<>(productService.save(product, multipartFile), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Iterable<Product>> findByCategoryId(@PathVariable Integer categoryId){
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }


}
