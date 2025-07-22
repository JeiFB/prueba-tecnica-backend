package com.tcc.taskmanager.domain.usecase;

import com.tcc.taskmanager.domain.api.IUserServicePort;
import com.tcc.taskmanager.domain.models.User;


import com.tcc.taskmanager.domain.spi.persistence.IUserPersistencePort;
import lombok.extern.log4j.Log4j2;
import java.util.List;

@Log4j2
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void createUser(User user) {
        try{
            userPersistencePort.createUser(user);
        }catch(Exception e){
            log.error("Error creating user", e);
            throw new IllegalArgumentException("Error to create User");
        }
    }
    @Override
    public User getUserById(Long userId) {
        return userPersistencePort.getUserById(userId);
    }
    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }
}
