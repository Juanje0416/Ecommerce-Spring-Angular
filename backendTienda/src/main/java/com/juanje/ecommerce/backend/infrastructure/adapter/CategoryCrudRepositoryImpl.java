package com.juanje.ecommerce.backend.infrastructure.adapter;

import com.juanje.ecommerce.backend.domain.model.Category;
import com.juanje.ecommerce.backend.domain.port.ICategoryRepository;
import com.juanje.ecommerce.backend.infrastructure.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {
    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    // Constructor para inicializar las dependencias
    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public Category save(Category category) {
        return  categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategoryList(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        //en el caso que busquemos un registro u objeto que no exista nos va a mandar una excepción, aquí se maneja esa excepción
        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).orElseThrow(//este metodo es proporcionado por la interfaz CrudRepository
                ()->new RuntimeException("Categoría con id: "+id+ " no existe")
        ));
    }
    //elimina un objeto de tipo category por su id
    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Categoría con id: "+id+ " no existe")
        );
        iCategoryCrudRepository.deleteById(id);
    }
}
