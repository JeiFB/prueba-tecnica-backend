package com.tcc.taskmanager.infraestructure.security;

public final class SecurityConstants {

    private SecurityConstants() {}

    // JWT Token
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    
    // JWT Expiration
    public static final long EXPIRATION_TIME = 86_400_000; // 1 día en ms
} 