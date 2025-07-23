# Gestor de Tareas - API Backend

API RESTful construida con Spring Boot para la gestión de tareas y usuarios. Este backend proporciona los servicios necesarios para una aplicación de frontend, incluyendo autenticación, CRUD de tareas y un dashboard de estadísticas.

## ✨ Características

- **Autenticación y Autorización:** Sistema seguro basado en JSON Web Tokens (JWT).
- **Gestión de Tareas (CRUD):** Operaciones completas para crear, leer, actualizar y eliminar tareas.
- **Filtrado y Búsqueda:** Endpoints para filtrar tareas por estado, prioridad y buscar por título.
- **Gestión de Usuarios:** Registro y consulta de usuarios.
- **Dashboard:** Endpoint para obtener estadísticas clave sobre el estado de las tareas.
- **Documentación de API:** Documentación interactiva y completa con Swagger (OpenAPI).
- **Contenerización:** Lista para desplegarse con Docker.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3
- **Base de Datos:** SQL Server (o cualquier base de datos compatible con JPA)
- **Autenticación:** Spring Security, JWT
- **Gestión de Dependencias:** Maven
- **Documentación:** Springdoc (Swagger/OpenAPI)
- **Contenerización:** Docker

## 🚀 Cómo Empezar

Sigue estas instrucciones para tener una copia del proyecto funcionando en tu máquina local.

### Prerrequisitos

Asegúrate de tener instalado lo siguiente:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/) (para la ejecución en contenedor)
- Un cliente Git para clonar el repositorio.

### 1. Clonar el Repositorio

```bash
git clone https://github.com/JeiFB/prueba-tecnica-backend
cd prueba-tecnica-back
```

### 2. Configuración de Variables de Entorno

La aplicación se configura a través de variables de entorno o modificando el archivo `src/main/resources/application.properties`.

Crea un archivo `.env` en la raíz del proyecto (opcional, para desarrollo local) o configura estas variables en tu sistema:

```properties
# URL de conexión a tu base de datos
SPRING_DATASOURCE_URL=(NO AUTORIZADO)

# Credenciales de la base de datos
SPRING_DATASOURCE_USERNAME=(NO AUTORIZADO)
SPRING_DATASOURCE_PASSWORD=(NO AUTORIZADO)

# Secreto para firmar los tokens JWT (debe ser una clave de 512 bits en Base64)
JWT_SECRET=(NO AUTORIZADO)
```

### 3. Ejecución Local (sin Docker)

Puedes ejecutar la aplicación directamente usando el wrapper de Maven.

```bash
# En Windows
./mvnw spring-boot:run

# En Linux/macOS
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8080`.

### 4. Ejecución con Docker

El proyecto incluye un `Dockerfile` multi-etapa que compila y ejecuta la aplicación en un contenedor.

1.  **Construir la imagen de Docker:**
    ```bash
    docker build -t taskmanager-api .
    ```

2.  **Ejecutar el contenedor:**
    Asegúrate de pasar las variables de entorno necesarias.

    ```bash
    docker run -p 8080:8080 \
      -e SPRING_DATASOURCE_URL=(NO AUTORIZADO) \
      -e SPRING_DATASOURCE_USERNAME=(NO AUTORIZADO) \
      -e SPRING_DATASOURCE_PASSWORD=(NO AUTORIZADO) \
      -e JWT_SECRET=(NO AUTORIZADO) \
      taskmanager-api
    ```
    **Nota:** `host.docker.internal` permite al contenedor conectarse a servicios que se ejecutan en tu máquina host (tu PC).

## 📚 Documentación de la API

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de Swagger en la siguiente URL:

[**http://localhost:8080/swagger-ui/index.html**](http://localhost:8080/swagger-ui/index.html)

Desde esta interfaz, puedes explorar todos los endpoints, ver los modelos de datos y probar la API directamente. Para los endpoints protegidos, primero obtén un token desde `/auth/login` y luego úsalo en el botón "Authorize" de Swagger. 