package com.tcc.taskmanager.application.handler.impl;

import com.tcc.taskmanager.application.dtos.request.UserRequestDto;
import com.tcc.taskmanager.application.dtos.response.UserResponseDto;
import com.tcc.taskmanager.application.handler.IUserHandler;
import com.tcc.taskmanager.application.mapper.IUserRequestMapper;
import com.tcc.taskmanager.application.mapper.IUserResponseMapper;
import com.tcc.taskmanager.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort iUserServicePort;
    private final IUserRequestMapper iUserRequestMapper;
    private final IUserResponseMapper iUserResponseMapper;

    @Override
    public void createUser(UserRequestDto userRequestDto) {
        iUserServicePort.createUser(iUserRequestMapper.toUser(userRequestDto));
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        return iUserResponseMapper.toResponse(iUserServicePort.getUserById(userId));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return iUserServicePort.getAllUsers().stream()
                .map(iUserResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
