package com.tcc.taskmanager.infraestructure.input;

import com.tcc.taskmanager.application.dtos.request.UserRequestDto;
import com.tcc.taskmanager.application.dtos.response.UserResponseDto;
import com.tcc.taskmanager.application.handler.IUserHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.tcc.taskmanager.infraestructure.input.ApiRoutes.User.*;

@RestController
@RequestMapping(BASE_URL)
public class UserRestController {
    private final IUserHandler userHandler;

    public  UserRestController(IUserHandler userHandler){
        this.userHandler = userHandler;
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value = "id")Long id){
        return  ResponseEntity.ok(userHandler.getUserById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        try {
            userHandler.createUser(userRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
