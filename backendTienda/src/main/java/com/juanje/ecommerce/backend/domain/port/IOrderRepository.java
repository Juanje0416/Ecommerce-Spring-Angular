package com.juanje.ecommerce.backend.domain.port;

import com.juanje.ecommerce.backend.domain.model.Order;

public interface IOrderRepository {
    Order save(Order order);

    Order findById(Integer id);

    Iterable<Order> findAll();

    Iterable<Order> findByUserId(Integer id);

    void updateStateById(Integer id,String state);

    void deleteById(Integer id);

}
