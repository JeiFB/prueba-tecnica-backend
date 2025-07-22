package com.tcc.taskmanager.application.mapper;

import com.tcc.taskmanager.domain.models.User;
import com.tcc.taskmanager.application.dtos.request.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserRequestMapper {
    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "createAt",  expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateAt",  expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "active",    constant = "true")
    User toUser(UserRequestDto userRequestDto);

}
