package com.tcc.taskmanager.infraestructure.input;

import com.tcc.taskmanager.application.dtos.request.LoginRequestDto;
import com.tcc.taskmanager.infraestructure.security.JwtUtil;
import com.tcc.taskmanager.infraestructure.output.jpa.entity.UserEntity;
import com.tcc.taskmanager.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints para el inicio de sesión y gestión de tokens")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica a un usuario y devuelve un token JWT si las credenciales son válidas.")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok().body(java.util.Map.of("access_token", token));
    }
} 