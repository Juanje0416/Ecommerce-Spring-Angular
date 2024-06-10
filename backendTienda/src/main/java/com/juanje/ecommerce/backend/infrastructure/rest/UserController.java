package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.application.UserService;
import com.juanje.ecommerce.backend.domain.model.Product;
import com.juanje.ecommerce.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

//esta clase maneja los usuarios, los controladores son llamados y los services son los que hacen el trabajo
@RestController
//http://localhost:8085
@RequestMapping("/api/v1/users")
//http://localhost:8085/api/v1/users
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //los metodos de las api rest deben ser public para que también sean accesibles por el cliente
    //este metodo guarda un usuario
    @PostMapping //levanto un endpoint para poder registrar un usuario
    public ResponseEntity<User> register(@RequestBody User user) {
        log.info("Clave encriptada: {}", passwordEncoder.encode(user.getPassword()));
        //uso el obj user para setear un nuevo password, con el password encode y el metodo encode, con el get consigo el password de ese usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }


    //Pathvariable lo que hace es que cuando me envian una variabele mediante la url la convierto a este tipo de datos
    //http://localhost:8085/api/v1/users/3 por ejemplo
    //esta cadena identifica cuantos parametros voy a recibir desde el cliente
    //para probar con postman poner:"/users{id}" y comprobar el correcto funcionamiento del controlador
    //este metodo obtiene un usuario
    @GetMapping("/{id}") //cuando obtengo algo del back uso getMapping
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
