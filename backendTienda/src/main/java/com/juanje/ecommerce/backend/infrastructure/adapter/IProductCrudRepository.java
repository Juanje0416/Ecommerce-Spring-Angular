package com.juanje.ecommerce.backend.infrastructure.adapter;


import com.juanje.ecommerce.backend.infrastructure.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {

}

