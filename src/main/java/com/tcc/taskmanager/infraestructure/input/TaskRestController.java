package com.tcc.taskmanager.infraestructure.input;

import com.tcc.taskmanager.application.dtos.request.TaskRequestDto;
import com.tcc.taskmanager.application.dtos.request.TaskFilterRequestDto;
import com.tcc.taskmanager.application.dtos.response.TaskResponseDto;
import com.tcc.taskmanager.application.handler.ITaskHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Gestión de Tareas", description = "Endpoints para crear, leer, actualizar y eliminar tareas")
public class TaskRestController {
    private final ITaskHandler taskHandler;

    @PostMapping("/")
    @Operation(summary = "Crear una nueva tarea")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> createTask(@RequestBody TaskRequestDto dto) {
        taskHandler.createTask(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por su ID")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskHandler.getTaskById(id));
    }

    @GetMapping("/")
    @Operation(summary = "Obtener todas las tareas")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok(taskHandler.getAllTasks());
    }

    @PostMapping("/filter")
    @Operation(summary = "Filtrar tareas por diferentes criterios")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<TaskResponseDto>> filterTasks(@RequestBody TaskFilterRequestDto filters) {
        return ResponseEntity.ok(taskHandler.findTasksByFilters(filters));
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar tareas por título (paginado)")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Page<TaskResponseDto>> searchByTitle(
            @RequestParam String title,
            Pageable pageable) {
        return ResponseEntity.ok(taskHandler.searchByTitle(title, pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una tarea existente")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        taskHandler.updateTask(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskHandler.deleteTask(id);
        return ResponseEntity.ok().build();
    }
} 