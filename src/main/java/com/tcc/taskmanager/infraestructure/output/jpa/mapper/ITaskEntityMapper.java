package com.tcc.taskmanager.infraestructure.output.jpa.mapper;

import com.tcc.taskmanager.domain.models.Task;
import com.tcc.taskmanager.infraestructure.output.jpa.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITaskEntityMapper {
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "userId", source = "user.id")
    Task toDomain(TaskEntity entity);

    TaskEntity toEntity(Task task);
} 