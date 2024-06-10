package com.juanje.ecommerce.backend.infrastructure.config;


import com.juanje.ecommerce.backend.application.*;
import com.juanje.ecommerce.backend.domain.port.ICategoryRepository;
import com.juanje.ecommerce.backend.domain.port.IOrderRepository;
import com.juanje.ecommerce.backend.domain.port.IProductRepository;
import com.juanje.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//los beans son componentes que sirven para ser inyectados en cualquier parte de la app
//unos de los objetivos que tiene esta clase es tener más control sobre los beans de mi aplicación

@Configuration
public class BeanConfiguration {

    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }

    @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFileService uploadFileService){
        return  new ProductService(iProductRepository, uploadFileService);
    }

    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository){
        return new OrderService(iOrderRepository);
    }

    @Bean
    public UploadFileService uploadFileService(){ //este bean es necesario ya que sino me daría fallos al declarar mi Uploadfile service en mi bean product
        return new UploadFileService();
    }

    @Bean
    public RegistrationService registrationService(IUserRepository iUserRepository){
        return new RegistrationService(iUserRepository);
    }

}
