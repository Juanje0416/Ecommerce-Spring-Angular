package com.juanje.ecommerce.backend.infrastructure.entity;

//esta clase se va a mapear como una tabla en la BBDD en la cual se van a gestionar los registros de clase categoría
//la categoría me va a permitir agrupar productos

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class CategoryEntity {
    //los atributos de esta clase se agrupan como columnas en mi BBDD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //incrementa de forma automatica la id
    private Integer id;
    private String name;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

}
