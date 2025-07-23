package com.tcc.taskmanager.domain.usecase;

import com.tcc.taskmanager.application.dtos.request.TaskFilterRequestDto;
import com.tcc.taskmanager.application.dtos.response.DashboardStatsDto;
import com.tcc.taskmanager.domain.api.ITaskServicePort;
import com.tcc.taskmanager.domain.models.Task;
import com.tcc.taskmanager.domain.models.TaskPriority;
import com.tcc.taskmanager.domain.models.TaskStatus;
import com.tcc.taskmanager.domain.spi.persistence.ITaskPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tcc.taskmanager.domain.exceptions.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static com.tcc.taskmanager.domain.constants.DomainConstants.TASK_NOT_FOUND_MESSAGE;

public class TaskUseCase implements ITaskServicePort {
    private final ITaskPersistencePort taskPersistencePort;

    public TaskUseCase(ITaskPersistencePort taskPersistencePort) {
        this.taskPersistencePort = taskPersistencePort;
    }

    @Override
    public void createTask(Task task) {
        taskPersistencePort.createTask(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return Optional.ofNullable(taskPersistencePort.getTaskById(id))
                .orElseThrow(() -> new ResourceNotFoundException(TASK_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskPersistencePort.getAllTasks();
    }

    @Override
    public void updateTask(Task task) {
        getTaskById(task.getId());
        taskPersistencePort.updateTask(task);
    }

    @Override
    public void deleteTask(Long id) {
        getTaskById(id);
        taskPersistencePort.deleteTask(id);
    }

    @Override
    public List<Task> findTasksByFilters(TaskFilterRequestDto filters) {
        return taskPersistencePort.findTasksByFilters(filters);
    }

    @Override
    public DashboardStatsDto getDashboardStats() {
        List<Task> tasks = taskPersistencePort.getAllTasks();
        DashboardStatsDto stats = new DashboardStatsDto();
        stats.setTotalTasks(tasks.size());
        stats.setCompleted((int) tasks.stream().filter(t -> t.getStatus() == TaskStatus.DONE).count());
        stats.setInProgress((int) tasks.stream().filter(t -> t.getStatus() == TaskStatus.IN_PROGRESS).count());
        stats.setToDo((int) tasks.stream().filter(t -> t.getStatus() == TaskStatus.TO_DO).count());
        stats.setHighPriority((int) tasks.stream().filter(t -> t.getPriority() == TaskPriority.HIGH).count());
        stats.setMediumPriority((int) tasks.stream().filter(t -> t.getPriority() == TaskPriority.MEDIUM).count());
        stats.setLowPriority((int) tasks.stream().filter(t -> t.getPriority() == TaskPriority.LOW).count());
        stats.setOverdue((int) tasks.stream().filter(t -> t.getDueDate() != null && t.getDueDate().isBefore(LocalDate.now()) && t.getStatus() != TaskStatus.DONE).count());
        int total = tasks.size();
        int completed = stats.getCompleted();
        stats.setCompletionPercentage(total > 0 ? (completed * 100) / total : 0);
        return stats;
    }

    @Override
    public Page<Task> searchByTitle(String searchTerm, Pageable pageable) {
        return taskPersistencePort.searchByTitle(searchTerm, pageable);
    }
} 