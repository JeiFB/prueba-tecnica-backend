# Decisiones Técnicas

Este documento justifica las decisiones clave tomadas durante el desarrollo del proyecto "Gestor de Tareas".

### 1. Arquitectura de Backend

- **Elección:** **Spring Boot con Arquitectura Hexagonal (Puertos y Adaptadores)**
- **Justificación:**
    - **Separación de conceptos:** La arquitectura hexagonal aísla la lógica de negocio (el "dominio") de las tecnologías externas (frameworks, bases de datos, APIs). Esto hace que el código sea más fácil de mantener, probar y evolucionar.
    - **Intercambiabilidad:** Permite cambiar componentes externos sin afectar la lógica central. Por ejemplo, podríamos cambiar de SQL Server a PostgreSQL o de una API REST a gRPC con un impacto mínimo en el dominio.
    - **Testeabilidad:** El núcleo de la aplicación (los casos de uso) se puede probar de forma aislada, sin necesidad de levantar un servidor web o una base de datos, lo que resulta en tests más rápidos y fiables.

### 2. Autenticación y Seguridad

- **Elección:** **JWT (JSON Web Tokens) con Spring Security**
- **Justificación:**
    - **Stateless:** Los JWTs no requieren que el servidor mantenga un estado de sesión para cada usuario. El token contiene toda la información necesaria para la autenticación, lo que escala muy bien en arquitecturas de microservicios o sin estado.
    - **Compatibilidad:** Es un estándar ampliamente adoptado, ideal para la comunicación entre un backend y un frontend de tipo SPA (Single Page Application) como Angular, React o Vue.
    - **Seguridad:** Utilizar el algoritmo `HS512` con una clave secreta fuerte garantiza que los tokens no puedan ser manipulados por terceros.

### 3. Contenerización

- **Elección:** **Dockerfile Multi-etapa**
- **Justificación:**
    - **Optimización de la imagen:** La primera etapa ("build") utiliza una imagen pesada de Maven para compilar el proyecto, pero la etapa final utiliza una imagen JRE (Java Runtime Environment) mucho más ligera, que solo contiene lo necesario para ejecutar la aplicación. Esto reduce el tamaño de la imagen final, acelera los despliegues y mejora la seguridad al no incluir herramientas de compilación en producción.
    - **Portabilidad y consistencia:** Docker asegura que el entorno de ejecución sea el mismo en desarrollo, pruebas y producción, eliminando el clásico problema de "en mi máquina funciona".

### 4. Documentación de API

- **Elección:** **Springdoc OpenAPI (Swagger)**
- **Justificación:**
    - **Generación automática:** La documentación se genera a partir del propio código y las anotaciones, lo que asegura que siempre esté sincronizada con la implementación real.
    - **Interfaz interactiva:** Proporciona una UI (Swagger UI) que no solo permite leer la documentación, sino también probar los endpoints directamente desde el navegador, facilitando enormemente el desarrollo y la depuración tanto para el equipo de backend como para el de frontend.
    - **Estándar de la industria:** OpenAPI es el estándar de facto para definir APIs REST, lo que facilita la integración con otras herramientas y servicios. 