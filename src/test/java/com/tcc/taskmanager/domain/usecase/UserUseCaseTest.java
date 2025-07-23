package com.tcc.taskmanager.domain.usecase;

import com.tcc.taskmanager.domain.exceptions.EmailAlreadyExistsException;
import com.tcc.taskmanager.domain.exceptions.ResourceNotFoundException;
import com.tcc.taskmanager.domain.models.User;
import com.tcc.taskmanager.domain.spi.persistence.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password");
    }

    @Test
    void createUser_shouldThrowEmailAlreadyExistsException_whenEmailExists() {
        // Arrange
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> {
            userUseCase.createUser(user);
        });

        // Verify
        verify(userPersistencePort, never()).createUser(any(User.class));
    }

    @Test
    void createUser_shouldCallPersistencePort_whenEmailDoesNotExist() {
        // Arrange
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        // Act
        userUseCase.createUser(user);

        // Assert
        verify(userPersistencePort, times(1)).createUser(user);
    }

    @Test
    void getUserById_shouldThrowResourceNotFoundException_whenUserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userPersistencePort.getUserById(userId)).thenReturn(null);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            userUseCase.getUserById(userId);
        });
    }

    @Test
    void getUserById_shouldReturnUser_whenUserFound() {
        // Arrange
        Long userId = 1L;
        when(userPersistencePort.getUserById(userId)).thenReturn(user);

        // Act
        User foundUser = userUseCase.getUserById(userId);

        // Assert
        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
    }
} 