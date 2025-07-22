package com.tcc.taskmanager.application.handler.impl;

import com.tcc.taskmanager.application.dtos.request.TaskRequestDto;
import com.tcc.taskmanager.application.dtos.request.TaskFilterRequestDto;
import com.tcc.taskmanager.application.dtos.response.TaskResponseDto;
import com.tcc.taskmanager.application.dtos.response.DashboardStatsDto;
import com.tcc.taskmanager.application.handler.ITaskHandler;
import com.tcc.taskmanager.application.mapper.ITaskRequestMapper;
import com.tcc.taskmanager.domain.api.ITaskServicePort;
import com.tcc.taskmanager.domain.api.IUserServicePort;
import com.tcc.taskmanager.domain.models.Task;
import com.tcc.taskmanager.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskHandlerImpl implements ITaskHandler {
    private final ITaskServicePort taskServicePort;
    private final IUserServicePort userServicePort;
    private final ITaskRequestMapper taskRequestMapper;

    @Override
    public void createTask(TaskRequestDto taskRequestDto) {
        taskServicePort.createTask(taskRequestMapper.toTask(taskRequestDto));
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskServicePort.getTaskById(id);
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        dto.setDueDate(task.getDueDate());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        if (task.getUserId() != null) {
            User user = userServicePort.getUserById(task.getUserId());
            dto.setUserName(user != null ? user.getName() : null);
        }
        return dto;
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskServicePort.getAllTasks().stream().map(task -> {
            TaskResponseDto dto = new TaskResponseDto();
            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setCompleted(task.isCompleted());
            dto.setDueDate(task.getDueDate());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            if (task.getUserId() != null) {
                User user = userServicePort.getUserById(task.getUserId());
                dto.setUserName(user != null ? user.getName() : null);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateTask(Long id, TaskRequestDto taskRequestDto) {
        var task = taskRequestMapper.toTask(taskRequestDto);
        task.setId(id);
        taskServicePort.updateTask(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskServicePort.deleteTask(id);
    }

    @Override
    public List<TaskResponseDto> findTasksByFilters(TaskFilterRequestDto filters) {
        return taskServicePort.findTasksByFilters(filters).stream().map(task -> {
            TaskResponseDto dto = new TaskResponseDto();
            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setCompleted(task.isCompleted());
            dto.setDueDate(task.getDueDate());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            if (task.getUserId() != null) {
                User user = userServicePort.getUserById(task.getUserId());
                dto.setUserName(user != null ? user.getName() : null);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public DashboardStatsDto getDashboardStats() {
        return taskServicePort.getDashboardStats();
    }

    @Override
    public Page<TaskResponseDto> searchByTitle(String searchTerm, Pageable pageable) {
        return taskServicePort.searchByTitle(searchTerm, pageable)
            .map(task -> {
                TaskResponseDto dto = new TaskResponseDto();
                dto.setId(task.getId());
                dto.setTitle(task.getTitle());
                dto.setDescription(task.getDescription());
                dto.setCompleted(task.isCompleted());
                dto.setDueDate(task.getDueDate());
                dto.setStatus(task.getStatus());
                dto.setPriority(task.getPriority());
                if (task.getUserId() != null) {
                    User user = userServicePort.getUserById(task.getUserId());
                    dto.setUserName(user != null ? user.getName() : null);
                }
                return dto;
            });
    }
} 