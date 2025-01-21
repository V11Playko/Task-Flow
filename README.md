# E-Commerce Platform

## Descripción del Proyecto

Este proyecto es una plataforma de e-commerce diseñada con las mejores prácticas de desarrollo de software moderno, utilizando Java, Spring Boot y Maven. La arquitectura está basada en principios de Domain-Driven Design (DDD), proporcionando un diseño robusto y escalable.

## Características principales

- **API REST** para operaciones estándar CRUD.
- **GraphQL** para consultas complejas y personalizadas.
- Gestión de usuarios, productos, pedidos y categorías.
- Sistema de roles para clientes y administradores.
- Integración de carrito de compras y procesamiento de pagos.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Maven** como gestor de dependencias.
- **GraphQL** (usando `graphql-java` o la implementación de Spring Boot).
- **PostgreSQL** como base de datos relacional.
- **Hibernate** para mapeo ORM.
- **Docker** para contenerización.
- **Swagger** para documentar las APIs REST.

## Arquitectura

El sistema utiliza los principios de Domain-Driven Design (DDD):

- **Capa de Dominio**: Contiene entidades, agregados, value objects, y lógica de negocio.
- **Capa de Aplicación**: Implementa casos de uso que orquestan la interacción entre el dominio y la infraestructura.
- **Capa de Infraestructura**: Contiene controladores REST/GraphQL, repositorios y adaptadores externos.

### Diagrama de capas

```
+----------------------+
|    Infraestructura   |
| (REST, GraphQL, DB)  |
+----------------------+
|   Aplicación (CAS)   |
+----------------------+
|        Dominio       |
+----------------------+
```

## Estructura del Proyecto

El proyecto seguirá una estructura modular orientada a DDD:

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── playko/
│   │           └── ecommerce/
│   │               ├── domain/
│   │               ├── application/
│   │               ├── infrastructure/
│   │               └── shared/
│   └── resources/
│       └── application.properties
├── test/
    └── java/
        └── com/
            └── playko/
                └── ecommerce/
                    ├── domain/
                    ├── application/
                    ├── infrastructure/
                    └── shared/
```

## Instalación

1. Clona este repositorio:

```bash
git clone https://github.com/V11Playko/E-Commerce-with-API-REST-and-GraphQL
cd ecommerce-ddd
```

2. Configura la base de datos en `application.yml`.

3. Construye el proyecto:

```bash
mvn clean install
```

4. Ejecuta la aplicación:

```bash
mvn spring-boot:run
```

5. Accede a la API:

- **REST**: [http://localhost:8080/api](http://localhost:8080/api)

## Contribución

1. Haz un fork del repositorio.
2. Crea una rama con tus cambios:

```bash
git checkout -b feature/nueva-funcionalidad
```

3. Envía un Pull Request con una descripción clara de los cambios.
