# 🚀 Concurrent Web Server en Java

Este proyecto implementa un **servidor web concurrente** en Java utilizando `ExecutorService` y `ThreadPool` para manejar múltiples clientes de forma eficiente. Es ideal para propósitos académicos y de aprendizaje sobre programación concurrente, redes y pruebas multicliente.

---

## ✅ Características

- 🧵 Servidor concurrente con pool de hilos.
- 📁 Servidor de archivos estáticos (HTML, CSS).
- 🍪 Soporte básico para cookies HTTP.
- 🔁 Pruebas concurrentes con múltiples clientes simulados.
- 🛠️ Proyecto Maven estructurado profesionalmente.

---

## 📂 Estructura del proyecto

```
ConcurrentWebServer/
├── src/
│   ├── main/
│   │   ├── java/org/example/concurrentwebserver/
│   │   │   ├── WebServer.java
│   │   │   └── ClientHandler.java
│   │   └── resources/
│   │       └── index.html
│   └── test/
│       └── java/org/example/concurrentwebserver/
│           └── WebServerTest.java
├── pom.xml
└── README.md
```

---

## 🚀 Cómo ejecutar el servidor

### 🔧 Requisitos

- Java 17 o superior
- Maven

### ▶️ Ejecutar con Maven

Desde la raíz del proyecto:

```bash
mvn compile exec:java -Dexec.mainClass="org.example.concurrentwebserver.WebServer"
```

> Asegúrate de tener un archivo `index.html` en `src/main/resources`.

### 🌐 Prueba en navegador

Abre:

```
http://localhost:8080
```

---

## 🧪 Ejecutar pruebas concurrentes

```bash
mvn test
```

Este comando simula múltiples clientes accediendo al servidor simultáneamente y valida que todos obtengan respuestas correctas.

---

## 📄 Ejemplo de respuesta

```
HTTP/1.1 200 OK
Content-Type: text/html
Set-Cookie: sessionId=abc123; Path=/
Content-Length: 1024

<html>...</html>
```

---

## 🛠 Tecnologías usadas

- 🟨 Java 17
- 🟦 Maven
- 🧪 JUnit 5

---

## ✍️ Autor

**Esteban Valencia**  
GitHub: [@tu_usuario](https://github.com/tu_usuario)

---

## 📌 Notas

- Puedes ampliar este proyecto para incluir:
    - Soporte para otros métodos HTTP (POST, etc.).
    - Soporte HTTPS (SSL).
    - Manejo de sesiones persistentes.
    - Templates dinámicos o servlets.

---

## 📸 Vista previa

```
Servidor escuchando en puerto 8080
Request: GET / HTTP/1.1
Request: GET /estilo.css HTTP/1.1
```
