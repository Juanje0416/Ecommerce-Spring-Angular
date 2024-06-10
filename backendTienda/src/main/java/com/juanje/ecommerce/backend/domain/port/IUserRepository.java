package com.juanje.ecommerce.backend.domain.port;

import com.juanje.ecommerce.backend.domain.model.Product;
import com.juanje.ecommerce.backend.domain.model.User;

public interface IUserRepository {
    User save(User user);
    User findByEmail(String email);
    User findById(Integer id);
    Iterable<User> findAll();
    void deleteById(Integer id);

}
