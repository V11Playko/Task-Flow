# Project Management System

## Descripción

Este es un sistema de gestión de proyectos colaborativos desarrollado en Java utilizando el framework Spring Boot. El proyecto sigue los principios de la arquitectura hexagonal, implementa APIs REST y GraphQL, y está gestionado con Maven.

El sistema permite a usuarios y equipos gestionar proyectos, tareas y colaboraciones de manera eficiente. Incluye funcionalidades como asignación de roles, tableros Kanban, integración con calendarios externos y notificaciones en tiempo real.

## Características principales

### Gestión de Usuarios
- Registro e inicio de sesión.
- Recuperación de contraseñas.
- Edición de perfiles de usuario.
- Invitación y desactivación de usuarios.

### Gestión de Proyectos
- Creación y configuración de proyectos.
- Asignación de roles y permisos.
- Archivado de proyectos completados.

### Gestión de Tareas
- Asignación y seguimiento de tareas.
- Creación de subtareas.
- Notificaciones de nuevas tareas y recordatorios.

### Visualización y Organización
- Tableros Kanban personalizables.
- Estadísticas básicas y reportes de rendimiento.

### Integraciones
- Sincronización con calendarios externos (Google Calendar).
- Registro de tiempo empleado en tareas.

## Tecnologías utilizadas

### Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Web
- Spring GraphQL

### Arquitectura
- Arquitectura Hexagonal
- API REST y GraphQL

### Base de datos
- PostgreSQL (o cualquier sistema compatible con JPA/Hibernate)

### Otros
- Maven: gestión de dependencias.
- JWT: autenticación.
- Swagger: documentación de APIs.

## Estructura del proyecto

```plaintext
projectManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/playko/eCommerce/
│   │   │       ├── application/
│   │   │       ├── domain/
│   │   │       ├── infrastructure/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migrations/
│   └── test/
├── pom.xml
└── README.md
```

## Instalación y configuración

### Prerrequisitos
- Java 17 o superior.
- Maven instalado.
- PostgreSQL u otra base de datos compatible.

### Pasos

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/V11Playko/Project-Management-with-Hexagonal-Architecture-and-GraphQL
   ```

2. Configurar las variables de entorno o editar el archivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/project_management
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=tu_secreto
   ```

3. Construir el proyecto:

   ```bash
   mvn clean install
   ```

4. Ejecutar la aplicación:

   ```bash
   mvn spring-boot:run
   ```

5. Acceder a la documentación de las APIs:
    - Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - GraphQL Playground: [http://localhost:8080/graphql](http://localhost:8080/graphql)

## Contribuir

Si deseas contribuir, por favor sigue los pasos a continuación:

1. Haz un fork del repositorio.
2. Crea una rama nueva para tu funcionalidad:
   ```bash
   git checkout -b nueva-funcionalidad
   ```
3. Realiza los cambios y haz commits descriptivos.
4. Envía un pull request.

## Licencia

Este proyecto está licenciado bajo la licencia APACHE. Consulta el archivo LICENSE para más información.
