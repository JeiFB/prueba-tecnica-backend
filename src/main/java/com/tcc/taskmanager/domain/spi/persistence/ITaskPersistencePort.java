package com.tcc.taskmanager.domain.spi.persistence;

import com.tcc.taskmanager.domain.models.Task;
import com.tcc.taskmanager.application.dtos.request.TaskFilterRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ITaskPersistencePort {
    void createTask(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    void updateTask(Task task);
    void deleteTask(Long id);
    List<Task> findTasksByFilters(TaskFilterRequestDto filters);
    Page<Task> searchByTitle(String searchTerm, Pageable pageable);
}
