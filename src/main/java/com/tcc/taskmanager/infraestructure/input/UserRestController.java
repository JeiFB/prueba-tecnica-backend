package com.tcc.taskmanager.infraestructure.input;

import com.tcc.taskmanager.application.dtos.request.UserRequestDto;
import com.tcc.taskmanager.application.dtos.response.UserResponseDto;
import com.tcc.taskmanager.application.handler.IUserHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/user")
@Tag(name = "Gestión de Usuarios", description = "Endpoints para la gestión de usuarios")
public class UserRestController {
    private final IUserHandler userHandler;

    public  UserRestController(IUserHandler userHandler){
        this.userHandler = userHandler;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su ID")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value = "id")Long id){
        return  ResponseEntity.ok(userHandler.getUserById(id));
    }

    @GetMapping("/")
    @Operation(summary = "Obtener todos los usuarios")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUsers());
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un nuevo usuario en el sistema. Este endpoint es público.")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        try {
            userHandler.createUser(userRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
