# ForoHub API 🗣️

Una API RESTful desarrollada con **Spring Boot**, **Spring Data JPA** y **MySQL** que permite la gestión de usuarios, temas (tópicos) y respuestas dentro de un sistema de foros. Esta API soporta operaciones CRUD y está preparada para extenderse fácilmente con funcionalidades como autenticación, paginación, y más.

---

## 🧠 Funcionalidades principales

- Registro y autenticación de usuarios (JWT)
- Creación, visualización, actualización y eliminación de **tópicos**
- Publicación de **respuestas** a tópicos por parte de otros usuarios
- Filtrado y paginación de tópicos
- Gestión de usuarios y perfiles
- Auditoría básica y control de errores
- Migraciones de base de datos automáticas con **Flyway**

---
## 📌 Tecnologías

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL 8
- Maven
- Jakarta Validation
- Postman / Insomnia (para pruebas)
- Flyway (para migraciones de base de datos) 🚧 (configurable)
---
## 📂 Estructura del proyecto

```bash
src
├── main
│   ├── java
│   │   └── cjmp.desafio.foroHub
│   │       ├── controller
│   │       ├── domain
│   │       │   ├── curso
│   │       │   ├── perfil
│   │       │   ├── respuesta
│   │       │   ├── topico
│   │       │   └── usuario
│   │       └── infra
│   │           ├── config
│   │           ├── exceptions
│   │           └── security
│   └── resources
│       ├── application.properties
│       └── db.migration
│   │       └── V*__files.sql # Archivos de migración Flyway (.sql)
```

---

## 🛠️ Instalación y Ejecución

1. Clona el repositorio:
   ```
   git clone https://github.com/tu-usuario/forohub.git
   cd forohub
   ```
2. Configura tu base de datos MySQL (por ejemplo en application.properties):
   ```
    spring.datasource.url=jdbc:mysql://localhost:3306/forohub_api
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.flyway.enabled=true
   ```
3. Ejecuta la aplicación
   Desde tu IDE (IntelliJ, NetBeans, Eclipse) o con Maven:
    ```
   ./mvnw spring-boot:run
    ```
4. Accede a la documentación Swagger:
    ```
   http://localhost:8080/swagger-ui/index.html
   ```
   
---
## 🔐 Autenticación y seguridad

El sistema usa JWT para la autenticación. Los endpoints protegidos requieren un token válido.
### Endpoints públicos:
* POST /login – Autenticación con email y contraseña (devuelve token)
* POST /usuarios – Registro de nuevo usuario
### Endpoints protegidos (requieren Bearer Token):
* GET /topicos
* POST /topicos
* POST /topicos/{id}/respuestas
* DELETE /topicos/{id}

 entre otros...
## 🔐 Endpoints Principales
    | Método | Endpoint      | Descripción                  |
    | ------ | ------------- | ---------------------------- |
    | POST   | `/login`      | Autenticación (devuelve JWT) |
    | GET    | `/topicos`    | Listar todos los tópicos     |
    | POST   | `/topicos`    | Crear nuevo tópico           |
    | GET    | `/respuestas` | Listar respuestas            |
    | POST   | `/usuarios`   | Crear nuevo usuario          |
    | GET    | `/perfiles`   | Listar perfiles disponibles  |
* Nota: La mayoría de los endpoints están protegidos por JWT. Debes autenticarte primero para obtener un token y luego incluirlo en la cabecera Authorization: Bearer <token>.
---
## 📮 Ejemplo de autenticación
1. Login
    ```
        POST /login
        Content-Type: application/json
    
        {
        "email": "usuario@ejemplo.com",
        "password": "123456"
        }
    ```
2. Respuesta del token
    ```
        {
            "token": "eyJhbGciOiJIUzI1NiIsInR..."
        }
    ```

3.  Usar token en llamadas protegidas:
    ```
        Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
    ```
---
## 🧪 Pruebas
Puedes usar herramientas como Postman, Insomnia o curl para probar los endpoints de la API. Asegúrate de incluir el token JWT en las peticiones protegidas.

## ✨ Futuras mejoras
- Sistema de comentarios anidados
- Notificaciones a usuarios
- Buscador de tópicos por texto
- Moderación y reportes
- Documentación con Swagger/OpenAPI
---
## 📄 Licencia
Puedes utilizarlo y modificarlo libremente.
---
## 🤝 Autor
- ✍️ Crystian Muro **Desarrollador Java | Backend Developer**
- 🔗 GitHub: [github.com/13CrysM](https://github.com/13CrysM)
