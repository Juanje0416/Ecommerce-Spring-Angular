package com.juanje.ecommerce.backend.infrastructure.dto;

//un record es algo parecido a una clase, pero los record no tiene ni gets ni sets, solo atributos que se le pasan en el constructor
public record UserDTO(String username, String password) {

}
