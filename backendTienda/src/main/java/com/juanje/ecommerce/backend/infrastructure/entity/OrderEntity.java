package com.juanje.ecommerce.backend.infrastructure.entity;

import com.juanje.ecommerce.backend.domain.model.OrderState;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;

    @ManyToOne //un usuario puede tener muchas ordenes
    private UserEntity userEntity;

    //una orden va a tener una lista asociada de tipo orderProducts
    //una orden puede tener muchos objetos de tipo orderProduct
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.PERSIST) //cuando cree una orden con sus respectivos items los va a guardar de forma automatica
    private List<OrderProductEntity> orderProducts;
}
