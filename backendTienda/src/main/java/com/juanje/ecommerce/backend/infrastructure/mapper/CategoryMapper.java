package com.juanje.ecommerce.backend.infrastructure.mapper;

import com.juanje.ecommerce.backend.domain.model.Category;
import com.juanje.ecommerce.backend.infrastructure.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") //esto significa que el contenedor me va a proporcionar una instancia cuando la quiera usar en otra clase
public interface CategoryMapper {
    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "name", target = "name"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "dateUpdated", target = "dateUpdated")
            }
    )
    Category toCategory(CategoryEntity categoryEntity);

    //guarda objetos de tipo category y el iterable es parecido a una list
    Iterable<Category> toCategoryList(Iterable<CategoryEntity> categoryEntities);

    //hace lo contrario
    @InheritInverseConfiguration
    CategoryEntity toCategoryEntity(Category category);


}
