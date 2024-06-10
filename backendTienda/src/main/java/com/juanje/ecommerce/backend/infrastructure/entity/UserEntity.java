package com.juanje.ecommerce.backend.infrastructure.entity;

import com.juanje.ecommerce.backend.domain.model.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

//en este package van a estar todas las entidades(las clases que van a persistir en mi base de datos)

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String address;
    private String cellphone;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;
}
