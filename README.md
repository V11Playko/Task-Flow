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
- **MySQL** o **PostgreSQL** como base de datos relacional.
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

## Historias de Usuario

### MVP (Producto Mínimo Viable)

Estas historias están priorizadas para construir un sistema funcional básico:

#### Gestión de Usuarios

- Como administrador, quiero registrar nuevos usuarios para que puedan iniciar sesión en la plataforma.
- Como cliente, quiero registrarme y acceder a mi cuenta para realizar compras.

#### Gestión de Productos

- Como administrador, quiero agregar, actualizar y eliminar productos para mantener el catálogo actualizado.
- Como cliente, quiero buscar productos por nombre o categoría para encontrar lo que necesito.

#### Carrito de Compras

- Como cliente, quiero agregar productos a mi carrito para prepararme para la compra.
- Como cliente, quiero eliminar productos del carrito si cambio de opinión.

#### Gestión de Pedidos

- Como cliente, quiero confirmar mi pedido desde el carrito para completar mi compra.
- Como administrador, quiero ver una lista de pedidos para gestionarlos y actualizarlos.

#### Categorías de Productos

- Como administrador, quiero gestionar categorías de productos para clasificar mejor los artículos.

#### Consultas Avanzadas (GraphQL)

- Como cliente, quiero obtener productos filtrados por precio, popularidad y categorías para refinar mi búsqueda.

### Fase 2: Funcionalidades Avanzadas

#### Procesamiento de Pagos

- Como cliente, quiero pagar mis pedidos utilizando métodos seguros (ejemplo: tarjeta de crédito, PayPal).

#### Roles y Permisos

- Como administrador, quiero gestionar roles y permisos para controlar el acceso a ciertas funcionalidades.

#### Historial de Pedidos

- Como cliente, quiero ver mi historial de pedidos para realizar un seguimiento de mis compras.

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
git clone https://github.com/tuusuario/ecommerce-ddd.git
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
