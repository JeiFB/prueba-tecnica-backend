package com.tcc.taskmanager.infraestructure.constants;

public final class InfrastructureConstants {

    private InfrastructureConstants() {}

    // --- Mensajes para GlobalExceptionHandler ---
    public static final String GENERIC_ERROR_MESSAGE_KEY = "error";
    public static final String GENERIC_ERROR_MESSAGE_VALUE = "Ha ocurrido un error inesperado en el servidor.";

    // --- Textos para Swagger (OpenAPI) ---
    // Tags
    public static final String SWAGGER_TAG_AUTH = "Autenticación";
    public static final String SWAGGER_TAG_AUTH_DESCRIPTION = "Endpoints para el inicio de sesión y gestión de tokens";
    public static final String SWAGGER_TAG_TASKS = "Gestión de Tareas";
    public static final String SWAGGER_TAG_TASKS_DESCRIPTION = "Endpoints para crear, leer, actualizar y eliminar tareas";
    public static final String SWAGGER_TAG_USERS = "Gestión de Usuarios";
    public static final String SWAGGER_TAG_USERS_DESCRIPTION = "Endpoints para la gestión de usuarios";
    public static final String SWAGGER_TAG_DASHBOARD = "Dashboard";
    public static final String SWAGGER_TAG_DASHBOARD_DESCRIPTION = "Endpoints para obtener estadísticas del dashboard";

    // Descriptions
    public static final String SWAGGER_LOGIN_SUMMARY = "Iniciar sesión";
    public static final String SWAGGER_LOGIN_DESCRIPTION = "Autentica a un usuario y devuelve un token JWT si las credenciales son válidas.";

    // --- Textos para AuthController ---
    public static final String AUTH_INVALID_CREDENTIALS_MESSAGE = "Credenciales inválidas";
    public static final String AUTH_ACCESS_TOKEN_KEY = "access_token";

} 