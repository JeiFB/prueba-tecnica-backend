package com.tcc.taskmanager.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import com.tcc.taskmanager.domain.models.TaskStatus;
import com.tcc.taskmanager.domain.models.TaskPriority;
import java.time.LocalDate;

@Getter
@Setter
public class TaskRequestDto {
    @NotBlank
    private String title;
    private String description;
    private Long userId;
    private LocalDate dueDate;
    private TaskStatus status;
    private TaskPriority priority;
}
