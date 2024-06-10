package com.juanje.ecommerce.backend.application;

import com.juanje.ecommerce.backend.domain.model.User;
import com.juanje.ecommerce.backend.domain.port.IUserRepository;

//clase de servicio que nos permite registrar un usuario
public class RegistrationService {
    private final IUserRepository iUserRepository;

    public RegistrationService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User register(User user) {
        return iUserRepository.save(user);
    }

}
