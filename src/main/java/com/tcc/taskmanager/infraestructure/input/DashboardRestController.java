package com.tcc.taskmanager.infraestructure.input;

import com.tcc.taskmanager.application.dtos.response.DashboardStatsDto;
import com.tcc.taskmanager.application.handler.ITaskHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints para obtener estadísticas del dashboard")
public class DashboardRestController {
    private final ITaskHandler taskHandler;

    @GetMapping("/")
    @Operation(summary = "Obtener estadísticas del dashboard")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<DashboardStatsDto> getDashboardStats() {
        return ResponseEntity.ok(taskHandler.getDashboardStats());
    }
} 