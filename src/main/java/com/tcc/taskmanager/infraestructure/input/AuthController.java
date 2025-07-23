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
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import static com.tcc.taskmanager.infraestructure.constants.InfrastructureConstants.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = SWAGGER_TAG_AUTH, description = SWAGGER_TAG_AUTH_DESCRIPTION)
public class AuthController {
    // private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = SWAGGER_LOGIN_SUMMARY, description = SWAGGER_LOGIN_DESCRIPTION)
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).body(AUTH_INVALID_CREDENTIALS_MESSAGE);
        }
        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(401).body(AUTH_INVALID_CREDENTIALS_MESSAGE);
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok().body(java.util.Map.of(AUTH_ACCESS_TOKEN_KEY, token));
    }
} 