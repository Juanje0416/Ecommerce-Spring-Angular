package com.juanje.ecommerce.backend.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//order es la cabecera y ordenProduct son los detalles
@Data
public class Order {
    private Integer id;
    private LocalDateTime dateCreated;

    //una cabecera puede tener muchos detalles en sus productos
    private List<OrderProduct> orderProducts;
    private OrderState orderState;
    private Integer userId;

    public Order() {
        orderProducts = new ArrayList<>();
    }

    //metodo que calcula el total de la orden
    public BigDecimal getTotalOrderPrice() {
        return this.orderProducts.stream().map( ordenProduct -> ordenProduct.getTotalItem() ).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

}
