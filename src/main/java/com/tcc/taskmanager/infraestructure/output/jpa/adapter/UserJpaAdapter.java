package com.tcc.taskmanager.infraestructure.output.jpa.adapter;

import com.tcc.taskmanager.domain.models.User;
import com.tcc.taskmanager.domain.spi.persistence.IUserPersistencePort;
import com.tcc.taskmanager.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.tcc.taskmanager.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper iUserEntityMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(iUserEntityMapper.toEntity(user));
    }

    @Override
    public User getUserById(Long userId) {
        return iUserEntityMapper.toDomain(userRepository.getReferenceById(userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userEntityMapper.toUserList(userRepository.findAll());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
