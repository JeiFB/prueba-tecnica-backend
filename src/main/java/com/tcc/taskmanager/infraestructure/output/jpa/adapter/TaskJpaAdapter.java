package com.tcc.taskmanager.infraestructure.output.jpa.adapter;

import com.tcc.taskmanager.application.dtos.request.TaskFilterRequestDto;
import com.tcc.taskmanager.domain.models.Task;
import com.tcc.taskmanager.domain.models.TaskPriority;
import com.tcc.taskmanager.domain.models.TaskStatus;
import com.tcc.taskmanager.domain.spi.persistence.ITaskPersistencePort;
import com.tcc.taskmanager.infraestructure.output.jpa.entity.TaskEntity;
import com.tcc.taskmanager.infraestructure.output.jpa.entity.UserEntity;
import com.tcc.taskmanager.infraestructure.output.jpa.mapper.ITaskEntityMapper;
import com.tcc.taskmanager.infraestructure.output.jpa.repository.ITaskRepository;
import com.tcc.taskmanager.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static com.tcc.taskmanager.application.util.AppConstants.DATE_FORMAT;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class TaskJpaAdapter implements ITaskPersistencePort {
    private final ITaskRepository taskRepository;
    private final ITaskEntityMapper taskEntityMapper;
    private final IUserRepository userRepository;

    @Override
    public void createTask(Task task) {
        TaskEntity entity = taskEntityMapper.toEntity(task);
        if (task.getUserId() != null) {
            UserEntity user = userRepository.findById(task.getUserId()).orElse(null);
            entity.setUser(user);
        } else {
            entity.setUser(null);
        }
        taskRepository.save(entity);
    }

    @Override
    public Task getTaskById(Long id) {
        TaskEntity entity = taskRepository.getReferenceById(id);
        if (entity.getUser() != null) {
            entity.getUser().getName(); // Fuerza la carga del usuario
        }
        return taskEntityMapper.toDomain(entity);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream()
            .peek(entity -> {
                if (entity.getUser() != null) {
                    entity.getUser().getName(); // Fuerza la carga del usuario
                }
            })
            .map(taskEntityMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void updateTask(Task task) {
        TaskEntity entity = taskEntityMapper.toEntity(task);
        if (task.getUserId() != null) {
            UserEntity user = userRepository.findById(task.getUserId()).orElse(null);
            entity.setUser(user);
        } else {
            entity.setUser(null);
        }
        taskRepository.save(entity);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findTasksByFilters(TaskFilterRequestDto filters) {
        try {
            TaskStatus status = (filters.getStatus() != null && !filters.getStatus().isEmpty())
                ? TaskStatus.valueOf(filters.getStatus()) : null;
            TaskPriority priority = (filters.getPriority() != null && !filters.getPriority().isEmpty())
                ? TaskPriority.valueOf(filters.getPriority()) : null;
            LocalDate fromDate = (filters.getFromDate() != null && !filters.getFromDate().isEmpty())
                ? LocalDate.parse(filters.getFromDate(), DateTimeFormatter.ofPattern(DATE_FORMAT)) : null;
            LocalDate toDate = (filters.getToDate() != null && !filters.getToDate().isEmpty())
                ? LocalDate.parse(filters.getToDate(), DateTimeFormatter.ofPattern(DATE_FORMAT)) : null;
            return taskRepository.findByFilters(status, priority, fromDate, toDate)
                .stream()
                .map(taskEntityMapper::toDomain)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al filtrar tareas", e);
        }
    }

    @Override
    public Page<Task> searchByTitle(String searchTerm, Pageable pageable) {
        return taskRepository.findByTitleContainingIgnoreCase(searchTerm, pageable)
                .map(taskEntityMapper::toDomain);
    }
} 