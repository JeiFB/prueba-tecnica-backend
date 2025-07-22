package com.tcc.taskmanager.domain.spi.persistence;

import com.tcc.taskmanager.domain.models.User;
import java.util.List;

public interface IUserPersistencePort {
    void createUser(User user);
    User getUserById(Long userId);
    List<User> getAllUsers();
}
