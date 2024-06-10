package com.juanje.ecommerce.backend.domain.model;

import lombok.Data;

import java.math.BigDecimal;

//esta clase nos permite ver el valor de cada atributo antes de hacer el total de la orden
@Data
public class OrderProduct {
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;

    //esto representa el producto que el usuario está comprando
    private Integer productId;

    public BigDecimal getTotalItem(){
        return this.price.multiply(quantity);
    }

}
