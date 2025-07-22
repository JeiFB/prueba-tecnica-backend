package com.tcc.taskmanager.domain.api;

import com.tcc.taskmanager.domain.models.User;
import java.util.List;

public interface IUserServicePort {
    void createUser(User user);
    User getUserById(Long userId);
    List<User> getAllUsers();
}
