package com.juanje.ecommerce.backend.infrastructure.config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//esto es una clasa de configuración, aqui guardo mis credenciales y el ambiente que es sandbox
@Configuration
public class PaypalConfig {
    //asigno a cada variable las propiedades que tengo en mi pom, que son las propiedades que necesito para acceder a la api de paypal y realizar el pago
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Bean //creo un contexto mediante un bean que esté disponible cuando la pagina quiera conectarse o transaccionar en la api de paypal
    public APIContext apiContext(){
        return new APIContext(clientId, clientSecret, mode);
    }

}
