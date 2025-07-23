package com.tcc.taskmanager.domain.usecase;

import com.tcc.taskmanager.application.dtos.response.DashboardStatsDto;
import com.tcc.taskmanager.domain.exceptions.ResourceNotFoundException;
import com.tcc.taskmanager.domain.models.Task;
import com.tcc.taskmanager.domain.models.TaskPriority;
import com.tcc.taskmanager.domain.models.TaskStatus;
import com.tcc.taskmanager.domain.spi.persistence.ITaskPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskUseCaseTest {

    @Mock
    private ITaskPersistencePort taskPersistencePort;

    @InjectMocks
    private TaskUseCase taskUseCase;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setStatus(TaskStatus.TO_DO);
        task.setPriority(TaskPriority.MEDIUM);
        task.setDueDate(LocalDate.now().plusDays(1));
    }

    @Test
    void getTaskById_shouldThrowException_whenTaskNotFound() {
        when(taskPersistencePort.getTaskById(1L)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> taskUseCase.getTaskById(1L));
    }

    @Test
    void updateTask_shouldThrowException_whenTaskNotFound() {
        when(taskPersistencePort.getTaskById(task.getId())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> taskUseCase.updateTask(task));
        verify(taskPersistencePort, never()).updateTask(any(Task.class));
    }

    @Test
    void deleteTask_shouldThrowException_whenTaskNotFound() {
        when(taskPersistencePort.getTaskById(1L)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> taskUseCase.deleteTask(1L));
        verify(taskPersistencePort, never()).deleteTask(anyLong());
    }

    @Test
    void deleteTask_shouldCallPersistence_whenTaskFound() {
        when(taskPersistencePort.getTaskById(1L)).thenReturn(task);
        taskUseCase.deleteTask(1L);
        verify(taskPersistencePort, times(1)).deleteTask(1L);
    }

    @Test
    void getDashboardStats_shouldCalculateStatsCorrectly() {
        // Arrange
        Task completedTask = new Task();
        completedTask.setId(2L);
        completedTask.setStatus(TaskStatus.DONE);
        completedTask.setPriority(TaskPriority.HIGH);
        completedTask.setDueDate(LocalDate.now().minusDays(1));

        Task inProgressTask = new Task();
        inProgressTask.setId(3L);
        inProgressTask.setStatus(TaskStatus.IN_PROGRESS);
        inProgressTask.setPriority(TaskPriority.MEDIUM);
        inProgressTask.setDueDate(LocalDate.now().plusDays(2));

        Task overdueTask = new Task();
        overdueTask.setId(4L);
        overdueTask.setStatus(TaskStatus.TO_DO);
        overdueTask.setPriority(TaskPriority.LOW);
        overdueTask.setDueDate(LocalDate.now().minusDays(1));


        when(taskPersistencePort.getAllTasks()).thenReturn(Arrays.asList(task, completedTask, inProgressTask, overdueTask));

        // Act
        DashboardStatsDto stats = taskUseCase.getDashboardStats();

        // Assert
        assertEquals(4, stats.getTotalTasks());
        assertEquals(1, stats.getCompleted());
        assertEquals(1, stats.getInProgress());
        assertEquals(2, stats.getToDo());
        assertEquals(1, stats.getHighPriority());
        assertEquals(2, stats.getMediumPriority()); // Includes initial task
        assertEquals(1, stats.getLowPriority());
        assertEquals(1, stats.getOverdue());
        assertEquals(25, stats.getCompletionPercentage()); // 1 of 4
    }

    @Test
    void getDashboardStats_shouldHandleNoTasks() {
        // Arrange
        when(taskPersistencePort.getAllTasks()).thenReturn(Collections.emptyList());

        // Act
        DashboardStatsDto stats = taskUseCase.getDashboardStats();

        // Assert
        assertEquals(0, stats.getTotalTasks());
        assertEquals(0, stats.getCompleted());
        assertEquals(0, stats.getCompletionPercentage());
    }
} 