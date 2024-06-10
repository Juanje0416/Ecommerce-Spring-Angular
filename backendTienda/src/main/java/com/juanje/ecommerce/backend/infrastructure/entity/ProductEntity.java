package com.juanje.ecommerce.backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String code;
    private String description;
    private String urlImage;
    private BigDecimal price;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;


    //con esto internamente lo que hace el framework es crear una columna en la que se almacena la id del usuario que haga el registro del producto
    //y yambien de la categoria a la que pertenezca ese producto
    @ManyToOne //un usuario puede guardar uno o muchos productos
    private UserEntity userEntity;

    @ManyToOne //una category puede tener muchos productos y muchos productos pueden estar en una sola categoría
    private CategoryEntity categoryEntity;

}
