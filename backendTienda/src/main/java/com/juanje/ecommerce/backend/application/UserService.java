package com.juanje.ecommerce.backend.application;

import com.juanje.ecommerce.backend.domain.model.User;
import com.juanje.ecommerce.backend.domain.port.IUserRepository;

public class UserService {
    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User save(User user) {
        return iUserRepository.save(user);
    }

    public User findById (Integer id){
        return this.iUserRepository.findById(id);
    }

    public User findByEmail (String email){
        return iUserRepository.findByEmail(email);
    }

    public Iterable<User> findAll(){
        return this.iUserRepository.findAll();
    }

    public void deleteById(Integer id){
        User user = findById(id);

        //lo elimino en la BBDD
        this.iUserRepository.deleteById(id);
    }

}
