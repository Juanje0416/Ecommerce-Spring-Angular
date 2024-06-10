package com.juanje.ecommerce.backend.infrastructure.config;

import com.juanje.ecommerce.backend.infrastructure.jwt.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

//esta clase voy a configurar la autorización de ciertos recursos y la autenticación
//también desactivo filtros por defecto con la siguiente notación
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean //le indico que voy a utilizar una clave personalizada por mí para que no me genere de froma automatica una
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean  //defino que endpoints van a ser accesible para dependiendo del rol
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Deshabilita la protección contra falsificación de solicitudes entre sitios (CSRF)
        httpSecurity.cors(
                        cors -> cors.configurationSource(
                                request -> {
                                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                                    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
                                    corsConfiguration.setAllowedMethods(Arrays.asList("*"));
                                    corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
                                    return corsConfiguration;
                                }
                        )
                ).csrf(csrf -> csrf.disable()).authorizeHttpRequests(
                        //cuando vaya un petición hacia ese endpoint la deje pasar pero las demás que se necesiten usuario autorizado
                        // cuando vaya un petición hacia ese endpoint la deje pasar pero las demás que se necesiten usuario autorizado
                        // vamos en orden, del mas restrictivo al menos restrictivo
                        aut -> aut.requestMatchers("/api/v1/admin/categories/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/admin/products/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/admin/usercrud/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/admin/ordercrud/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/orders/**").hasRole("USER")
                                .requestMatchers("/api/v1/payments/success").permitAll()
                                .requestMatchers("/api/v1/payments/**").hasRole("USER")
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/api/v1/home/**").permitAll()
                                .requestMatchers("/api/v1/security/**").permitAll().anyRequest().authenticated()
                )
                // Agrega el filtro de autorización JWT después del filtro UsernamePasswordAuthenticationFilter
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // Construye y devuelve el objeto SecurityFilterChain configurado
        return httpSecurity.build();
    }


    @Bean //encriptar contraseñas
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
