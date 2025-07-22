package com.tcc.taskmanager.application.mapper;

import com.tcc.taskmanager.application.dtos.response.UserResponseDto;
import com.tcc.taskmanager.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    UserResponseDto toResponse(User user);
}
