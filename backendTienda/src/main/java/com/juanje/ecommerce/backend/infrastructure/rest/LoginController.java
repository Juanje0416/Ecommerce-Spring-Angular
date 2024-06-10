package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.application.UserService;
import com.juanje.ecommerce.backend.domain.model.User;
import com.juanje.ecommerce.backend.infrastructure.dto.JWTClient;
import com.juanje.ecommerce.backend.infrastructure.dto.UserDTO;
import com.juanje.ecommerce.backend.infrastructure.jwt.JWTGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }


    @PostMapping("/login") //autentico el usuario
    public ResponseEntity<JWTClient> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password())
        );
        //mantiene el usuario autenticado
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //una vez que el usuario está autenticado tenemos que ver a que recursos puede acceder, esto va avariar dependiendo del rol
        //el getAuthorities me permite obetener el rol
        //en la clase securityConfig especifico que recursos están disponibles para cada rol
        log.info("Rol de User: {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().toString());

        User user = userService.findByEmail(userDTO.username());

        String token = jwtGenerator.getToken(userDTO.username());//aquí está el token

        //me devuelve el tokewn y el id del usuario logado
        JWTClient jwtClient = new JWTClient(user.getId(), token, user.getUserType().toString());//paso el token de un string a un objeto

        return new ResponseEntity<>(jwtClient, HttpStatus.OK);
    }


}
