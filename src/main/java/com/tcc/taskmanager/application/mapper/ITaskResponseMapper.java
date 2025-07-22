package com.tcc.taskmanager.application.mapper;

import com.tcc.taskmanager.application.dtos.response.TaskResponseDto;
import com.tcc.taskmanager.domain.models.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITaskResponseMapper {
    TaskResponseDto toResponse(Task task);
} 