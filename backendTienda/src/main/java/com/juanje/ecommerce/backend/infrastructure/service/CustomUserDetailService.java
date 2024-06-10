package com.juanje.ecommerce.backend.infrastructure.service;

import com.juanje.ecommerce.backend.application.UserService;
import com.juanje.ecommerce.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadByUsername: {}", username);

        // Busco al usuario por su nombre de usuario, que en mi caso es su correo electrónico
        User user = userService.findByEmail(username);

        // Construyo y devuelvo los detalles del usuario autenticado utilizando los datos recuperados
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // Establezco el nombre de usuario como el correo electrónico del usuario
                .password(user.getPassword()) // Establezco la contraseña del usuario
                .roles(user.getUserType().name()) // Asigno los roles del usuario, que vienen determinados por su tipo de usuario
                .build(); // Construyo el objeto UserDetails

    }



}
