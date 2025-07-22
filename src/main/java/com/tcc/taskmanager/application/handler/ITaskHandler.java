package com.tcc.taskmanager.application.handler;

import com.tcc.taskmanager.application.dtos.request.TaskRequestDto;
import com.tcc.taskmanager.application.dtos.response.TaskResponseDto;
import com.tcc.taskmanager.application.dtos.request.TaskFilterRequestDto;
import com.tcc.taskmanager.application.dtos.response.DashboardStatsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ITaskHandler {
    void createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto getTaskById(Long id);
    List<TaskResponseDto> getAllTasks();
    void updateTask(Long id, TaskRequestDto taskRequestDto);
    void deleteTask(Long id);
    List<TaskResponseDto> findTasksByFilters(TaskFilterRequestDto filters);
    DashboardStatsDto getDashboardStats();
    Page<TaskResponseDto> searchByTitle(String searchTerm, Pageable pageable);
} 