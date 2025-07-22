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


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskRestController {
    private final ITaskHandler taskHandler;

    @PostMapping("/")
    public ResponseEntity<Void> createTask(@RequestBody TaskRequestDto dto) {
        taskHandler.createTask(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskHandler.getTaskById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok(taskHandler.getAllTasks());
    }

    @PostMapping("/filter")
    public ResponseEntity<List<TaskResponseDto>> filterTasks(@RequestBody TaskFilterRequestDto filters) {
        return ResponseEntity.ok(taskHandler.findTasksByFilters(filters));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TaskResponseDto>> searchByTitle(
            @RequestParam String title,
            Pageable pageable) {
        return ResponseEntity.ok(taskHandler.searchByTitle(title, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        taskHandler.updateTask(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskHandler.deleteTask(id);
        return ResponseEntity.ok().build();
    }
} 