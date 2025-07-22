package com.tcc.taskmanager.infraestructure.output.jpa.mapper;

import com.tcc.taskmanager.domain.models.User;
import com.tcc.taskmanager.infraestructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}