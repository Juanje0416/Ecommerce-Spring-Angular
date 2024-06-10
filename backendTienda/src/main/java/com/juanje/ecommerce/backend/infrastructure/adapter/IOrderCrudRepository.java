package com.juanje.ecommerce.backend.infrastructure.adapter;

import com.juanje.ecommerce.backend.domain.model.OrderState;
import com.juanje.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.juanje.ecommerce.backend.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

//este es un metodo personalizado en el que asigno los atributos que quiero y necesito
//este metodo obtienes las ordenes por el atributo userEntity, si le paso un usuario me va a dar sus ordenes
public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderState = :state WHERE o.id = :id")
    void updateStateById(Integer id, OrderState state);

    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);

}
