package com.juanje.ecommerce.backend.infrastructure.adapter;

import com.juanje.ecommerce.backend.infrastructure.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

//extiende de una interfaz más arriba(CrudRepository) la cual contiene un conjunto de métodos CRUD
public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer> {

}
