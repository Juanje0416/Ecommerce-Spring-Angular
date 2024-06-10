package com.juanje.ecommerce.backend.infrastructure.jwt;

import com.juanje.ecommerce.backend.infrastructure.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static com.juanje.ecommerce.backend.infrastructure.jwt.Constants.*;

public class JWTValidate {

    //valida que el token venga en la petición
    public static boolean tokenExists(HttpServletRequest request, HttpServletResponse response) {
        //contenido de la variable header
        String header = request.getHeader(HEADER_AUTHORIZATION);

        //compruebo de que me venga un token con este if
        if(header == null || !header.startsWith(TOKEN_BEARER_PREFIX) )
            return false;
        return true;
    }

    //valido que el token es el correcto
    public static Claims JWTValid(HttpServletRequest request){
        //dejo mi token limpio ya que siempre me viene con baerer delante
        String jwtToken = request.getHeader(HEADER_AUTHORIZATION).replace(TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
        .setSigningKey(getSignedKey(SUPER_SECRET_KEY))
        .build()
        .parseClaimsJws(jwtToken).getBody();

    }

    //metodo que me permite autenticar al usuario en el flujo de spring
    public static void setAuthentication(Claims claims, CustomUserDetailService customUserDetailService){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject()); //trae el nombre del usuario autenticado
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
