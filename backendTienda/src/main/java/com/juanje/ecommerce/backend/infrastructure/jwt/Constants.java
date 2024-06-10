package com.juanje.ecommerce.backend.infrastructure.jwt;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    public static final String SUPER_SECRET_KEY = "W5Kp2hN8vG6i3rT7mZ4sL1jC9qB2nF4dV8gJ3mK5lR9wX0sD2fV6hB7nM3jK9lO1pL7eR0";
    public static final long TOKEN_EXPIRATION_TIME = 1500000; //dura 15 minutos el token

    //este metodo me crea un hash apartir de mi clave secreta
    // el hash le da mayor seguridad en mi token
    public static Key getSignedKey(String secretKey){
        byte [] keyBytes =secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
