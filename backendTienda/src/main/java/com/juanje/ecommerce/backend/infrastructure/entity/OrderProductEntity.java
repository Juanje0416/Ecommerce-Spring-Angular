package com.juanje.ecommerce.backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_products")
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer productId;

    //defino una variable para que pueda asociar a que orden pertenece
    @ManyToOne
    private OrderEntity orderEntity;
}
