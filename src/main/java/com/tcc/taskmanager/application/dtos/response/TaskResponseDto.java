package com.tcc.taskmanager.application.dtos.response;

import lombok.Getter;
import lombok.Setter;
import com.tcc.taskmanager.domain.models.TaskStatus;
import com.tcc.taskmanager.domain.models.TaskPriority;
import java.time.LocalDate;

@Getter
@Setter
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    private TaskStatus status;
    private TaskPriority priority;
    private String userName;
} 