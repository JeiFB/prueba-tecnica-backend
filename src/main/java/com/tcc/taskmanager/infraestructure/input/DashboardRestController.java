package com.tcc.taskmanager.infraestructure.input;

import com.tcc.taskmanager.application.dtos.response.DashboardStatsDto;
import com.tcc.taskmanager.application.handler.ITaskHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.tcc.taskmanager.infraestructure.input.ApiRoutes.Dashboard.*;

@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
public class DashboardRestController {
    private final ITaskHandler taskHandler;

    @GetMapping("")
    public ResponseEntity<DashboardStatsDto> getDashboardStats() {
        return ResponseEntity.ok(taskHandler.getDashboardStats());
    }
} 