# MobileFix - Repair Order Management

Esta es una aplicación de ejemplo para gestionar órdenes de reparación de dispositivos móviles, construida con Spring Boot.

## Cómo ejecutar la aplicación

1.  **Requisitos previos:**
    *   Java 21
    *   Maven

2.  **Compilar y ejecutar:**
    Desde la raíz del proyecto, ejecuta el siguiente comando:
    ```bash
    mvn spring-boot:run
    ```

3.  **Acceder a la aplicación:**
    *   La aplicación estará disponible en `http://localhost:8080`.
    *   La consola de la base de datos H2 está en `http://localhost:8080/h2-console`.
        *   **JDBC URL:** `jdbc:h2:mem:testdb`
        *   **Username:** `sa`
        *   **Password:** (dejar en blanco)

## Usuarios de prueba

Puedes iniciar sesión con los siguientes usuarios:

*   **Rol:** `USER`
    *   **Username:** `user`
    *   **Password:** `user123`
*   **Rol:** `TECH`
    *   **Username:** `tech`
    *   **Password:** `tech123`
*   **Rol:** `ADMIN`
    *   **Username:** `admin`
    *   **Password:** `admin123`

## Endpoints de la API REST

A continuación se listan los principales endpoints de la API.

### Repair Orders (`/api/orders`)

*   `GET /`: Obtiene órdenes (filtradas por rol).
*   `POST /`: Crea una nueva orden de reparación (solo `USER`).
*   `PUT /{id}/assign/{techId}`: Asigna un técnico a una orden (solo `ADMIN`).
*   `PUT /{id}/status`: Cambia el estado de una orden (solo `TECH` y `ADMIN`).
*   `DELETE /{id}`: Elimina una orden (restringido por rol y estado).

### Devices (`/api/devices`)

*   `GET /`: Obtiene todos los dispositivos.
*   `POST /`: Crea un nuevo dispositivo (solo `ADMIN`).
*   `PUT /{id}`: Actualiza un dispositivo (solo `ADMIN`).
*   `DELETE /{id}`: Elimina un dispositivo (solo `ADMIN`).

### Users (`/api/users`)

*   `GET /`: Obtiene todos los usuarios (solo `ADMIN`).
*   `POST /`: Crea un nuevo usuario (solo `ADMIN`).
