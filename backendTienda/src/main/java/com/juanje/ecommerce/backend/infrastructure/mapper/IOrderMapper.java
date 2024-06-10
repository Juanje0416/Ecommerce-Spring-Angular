package com.juanje.ecommerce.backend.infrastructure.mapper;

import com.juanje.ecommerce.backend.domain.model.Order;
import com.juanje.ecommerce.backend.infrastructure.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//el atributo uses permite añadir el mapeo para un tipo de objeto que tiene esa clase internamente
@Mapper(componentModel = "spring", uses = {IOrderProductMapper.class})
public interface IOrderMapper {
    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "orderProducts", target = "orderProducts"),
                    @Mapping(source = "orderState", target = "orderState"),
                    @Mapping(source = "userEntity.id", target = "userId"),
            }
    )

    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);
}
