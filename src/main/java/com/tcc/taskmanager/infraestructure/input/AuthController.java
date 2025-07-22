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
import static com.tcc.taskmanager.infraestructure.input.ApiRoutes.Auth.*;

@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        logger.info("Intentando login para email: {}", loginRequest.getEmail());
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            logger.warn("Usuario no encontrado para email: {}", loginRequest.getEmail());
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        logger.info("Usuario encontrado: {}", user.getEmail());
        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        logger.info("¿Contraseña coincide?: {}", passwordMatches);
        if (!passwordMatches) {
            logger.warn("Contraseña incorrecta para email: {}", loginRequest.getEmail());
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        logger.info("Login exitoso para email: {}", loginRequest.getEmail());
        return ResponseEntity.ok().body(java.util.Map.of("access_token", token));
    }
} 