package com.tcc.taskmanager.domain.usecase;

import com.tcc.taskmanager.domain.api.IUserServicePort;
import com.tcc.taskmanager.domain.models.User;


import com.tcc.taskmanager.domain.spi.persistence.IUserPersistencePort;
import lombok.extern.log4j.Log4j2;
import com.tcc.taskmanager.domain.exceptions.EmailAlreadyExistsException;
import com.tcc.taskmanager.domain.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import static com.tcc.taskmanager.domain.constants.DomainConstants.*;


@Log4j2
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void createUser(User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(String.format(EMAIL_ALREADY_EXISTS_MESSAGE, user.getEmail()));
        }
        userPersistencePort.createUser(user);
    }
    @Override
    public User getUserById(Long userId) {
        return Optional.ofNullable(userPersistencePort.getUserById(userId))
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE + userId));
    }
    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }
}
