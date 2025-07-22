package com.tcc.taskmanager.infraestructure.input;

public final class ApiRoutes {

    private ApiRoutes() {}

    public static final String API_BASE = "/api/v1";

    public static final class Auth {
        private Auth() {}
        public static final String BASE_URL = API_BASE + "/auth";
        public static final String LOGIN = "/login";
    }

    public static final class User {
        private User() {}
        public static final String BASE_URL = API_BASE + "/users";
        public static final String GET_BY_ID = "/{id}";
    }

    public static final class Task {
        private Task() {}
        public static final String BASE_URL = API_BASE + "/tasks";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";
        public static final String FILTER = "/filter";
    }

    public static final class Dashboard {
        private Dashboard() {}
        public static final String BASE_URL = API_BASE + "/dashboard";
    }
} 