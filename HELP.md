## Estructura del Proyecto

Estructura del proyecto más definida:
```
projectManagement/
├── HELP.md
├── HUs.md
├── LICENSE
├── README.md
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── playko/
│   │   │           └── eCommerce/
│   │   │               ├── application/
│   │   │               │   ├── dto/
│   │   │               │   │   ├── request/
│   │   │               │   │   └── response/
│   │   │               │   ├── handler/
│   │   │               │   │   └── impl/
│   │   │               │   ├── mapper/
│   │   │               │   │   ├── request/
│   │   │               │   │   └── response/
│   │   │               │   └── service/
│   │   │               ├── domain/
│   │   │               │   ├── api/
│   │   │               │   ├── exception/
│   │   │               │   ├── model/
│   │   │               │   ├── spi/
│   │   │               │   └── usecase/
│   │   │               ├── infrastructure/
│   │   │               │   ├── configuration/
│   │   │               │   │   ├── security/
│   │   │               │   │   │   ├── exception/
│   │   │               │   │   │   ├── userDetails/
│   │   │               │   │   │   └── jwt/
│   │   │               │   │   └── general/
│   │   │               │   ├── documentation/
│   │   │               │   │   ├── api/
│   │   │               │   │   └── graphql/
│   │   │               │   ├── exceptionhandler/
│   │   │               │   ├── input/
│   │   │               │   │   ├── rest/
│   │   │               │   │   └── graphql/
│   │   │               │   ├── out/
│   │   │               │   │   ├── jpa/
│   │   │               │   │   │   ├── adapter/
│   │   │               │   │   │   ├── entity/
│   │   │               │   │   │   ├── mapper/
│   │   │               │   │   │   └── repository/
│   │   │               │   │   ├── notification/
│   │   │               │   │   │   └── email/
│   │   │               │   │   └── external/
│   │   │               │   │       └── calendar/
│   │   │               │   └── templates/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── db/
│   │       │   └── migrations/
│   │       └── static/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── playko/
│       │           └── eCommerce/
│       │               ├── application/
│       │               ├── domain/
│       │               └── infrastructure/
│       └── resources/
└── mvnw
└── mvnw.cmd
```