# Scripts de Configuración y Ejecución

Este archivo contiene los scripts necesarios para la configuración de la base de datos y la ejecución del proyecto.

---

## 1. Scripts de Base de Datos (SQL)

Estos scripts están diseñados para SQL Server, pero pueden adaptarse a otras bases de datos.

### a. Creación de la Base de Datos

Ejecuta este comando en tu gestor de base de datos para crear la base de datos requerida por la aplicación.

```sql
CREATE DATABASE pruebaTecnica;
```

**Nota:** Las tablas (`users`, `tasks`, etc.) serán creadas y actualizadas automáticamente por Hibernate la primera vez que la aplicación se conecte a la base de datos, gracias a la propiedad `spring.jpa.hibernate.ddl-auto=update`.

### b. Inserción de Datos de Prueba (Opcional)

Puedes registrar usuarios a través del endpoint público `POST /user/register`. Sin embargo, si deseas insertar un usuario directamente en la base de datos para pruebas, puedes usar el siguiente script.

**Importante:** La contraseña debe ser un hash generado con BCrypt. El siguiente ejemplo utiliza una contraseña `123456` que ha sido hasheada.

```sql
-- Inserta un usuario de prueba con la contraseña "123456" hasheada con BCrypt
INSERT INTO users (name, email, password, is_active, create_at, update_at)
VALUES (
    'Usuario de Prueba',
    'test@example.com',
    '$2a$10$Y.lI92g57iN5b/PAH9EtS.B.wbWJc5VGSa2hasM52wAMh25X.Id.q', -- Contraseña: 123456
    1,
    GETDATE(),
    GETDATE()
);

-- Inserta algunas tareas de prueba asociadas al usuario anterior (ID = 1)
INSERT INTO tasks (title, description, status, priority, due_date, user_id, create_at, update_at)
VALUES
('Configurar el entorno de desarrollo', 'Instalar JDK, Maven y Docker', 'COMPLETED', 'HIGH', GETDATE() + 1, 1, GETDATE(), GETDATE()),
('Desarrollar la API de autenticación', 'Implementar login con JWT', 'IN_PROGRESS', 'HIGH', GETDATE() + 2, 1, GETDATE(), GETDATE()),
('Crear la documentación de la API', 'Añadir Swagger al proyecto', 'PENDING', 'MEDIUM', GETDATE() + 3, 1, GETDATE(), GETDATE());
```

---

## 2. Scripts de Ejecución

Estos son los comandos de consola para construir y ejecutar la aplicación.

### a. Ejecución Local (sin Docker)

```bash
# Navega a la raíz del proyecto
cd prueba-tecnica-back

# Ejecuta la aplicación
./mvnw spring-boot:run
```

### b. Construcción y Ejecución con Docker

```bash
# 1. Construir la imagen de Docker
docker build -t taskmanager-api .

# 2. Ejecutar el contenedor, pasando las variables de entorno
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:sqlserver://host.docker.internal:1433;databaseName=pruebaTecnica;encrypt=true;trustServerCertificate=true" \
  -e SPRING_DATASOURCE_USERNAME="tu_usuario_de_db" \
  -e SPRING_DATASOURCE_PASSWORD="tu_password_de_db" \
  -e JWT_SECRET="tu_jwt_secret_largo_y_seguro" \
  taskmanager-api
``` 