package com.juanje.ecommerce.backend.infrastructure.adapter;

import com.juanje.ecommerce.backend.domain.model.User;
import com.juanje.ecommerce.backend.domain.port.IUserRepository;
import com.juanje.ecommerce.backend.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {
    private final IUserCrudRepository iUserCrudRepository;
    private final UserMapper userMapper;

    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, UserMapper userMapper) {
        this.iUserCrudRepository = iUserCrudRepository;
        this.userMapper = userMapper;
    }

    //guarda un objeto y hace su conversión
    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toUser(iUserCrudRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("Usuario con email '" + email + "' no encontrado")
        ));
    }

    @Override //Quitamos el fallo, el cual es que nos devuelve un optional y no nos trae el objeto, lo resolvemos con el get()
    public User findById(Integer id) {
        return userMapper.toUser(iUserCrudRepository.findById(id).get());
    }

    @Override
    public Iterable<User> findAll() {
        return userMapper.toUsers(iUserCrudRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        iUserCrudRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Usuario con id: "+id+" no existe")
        );
        iUserCrudRepository.deleteById(id);
    }

}
