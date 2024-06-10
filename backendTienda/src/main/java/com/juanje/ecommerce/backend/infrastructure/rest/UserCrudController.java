package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.application.UserService;
import com.juanje.ecommerce.backend.domain.model.User;
import com.juanje.ecommerce.backend.domain.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/admin/usercrud")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserCrudController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserCrudController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping()
    public ResponseEntity<User> saveOrUpdate(@RequestBody User user) throws IOException {
        if (user.getId() == null) {
            // Crear nuevo usuario
            log.info("Creando nuevo usuario: {}", user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } else {
            // Actualizar usuario existente
            log.info("Actualizando usuario con ID: {}", user.getId());
            User existingUser = userService.findById(user.getId());
            if (existingUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            existingUser.setUsername(user.getUsername());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            existingUser.setCellphone(user.getCellphone());
            existingUser.setUserType(user.getUserType());

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            return new ResponseEntity<>(userService.save(existingUser), HttpStatus.OK);
        }
    }


    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
