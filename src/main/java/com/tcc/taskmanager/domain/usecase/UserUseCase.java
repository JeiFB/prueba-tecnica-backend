package com.tcc.taskmanager.domain.usecase;

import com.tcc.taskmanager.domain.api.IUserServicePort;
import com.tcc.taskmanager.domain.models.User;


import com.tcc.taskmanager.domain.spi.persistence.IUserPersistencePort;
import lombok.extern.log4j.Log4j2;
import com.tcc.taskmanager.domain.exceptions.EmailAlreadyExistsException;
import com.tcc.taskmanager.domain.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

@Log4j2
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void createUser(User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("El correo electrónico '" + user.getEmail() + "' ya está registrado.");
        }
        userPersistencePort.createUser(user);
    }
    @Override
    public User getUserById(Long userId) {
        return Optional.ofNullable(userPersistencePort.getUserById(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + userId));
    }
    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }
}
