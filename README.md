# Books API

REST API developed with Java and Spring Boot for searching and retrieving books using the Gutendex API.

The application exposes a simple and well-documented interface to search books by text and retrieve book details by their Project Gutenberg identifier.

## Features

* Search books by text query.
* Retrieve book details by Gutenberg ID.
* Integration with the Gutendex public API.
* OpenAPI specification-driven development.
* Swagger UI documentation.
* Hexagonal Architecture (Ports and Adapters).
* DTO mapping using MapStruct.
* Unit testing with JUnit and Mockito.

---

## Technologies

* Java 21
* Spring Boot 3
* Maven
* OpenAPI 3
* SpringDoc OpenAPI
* MapStruct
* JUnit 5
* Mockito

---

## Architecture

The project follows the principles of **Hexagonal Architecture (Ports and Adapters)**.

The business logic is isolated from external concerns such as REST controllers and third-party integrations.

### Layers

* **Domain**

    * Entities
    * Repository ports
    * Business services

* **Application**

    * Service implementations
    * Use case orchestration

* **Infrastructure**

    * Gutendex client
    * Repository adapters
    * External API DTOs

* **Web**

    * REST controllers
    * OpenAPI-generated DTOs

### Request Flow

```text
Controller
    ↓
Service
    ↓
Repository Port
    ↓
Repository Adapter
    ↓
Gutendex Client
    ↓
Gutendex API
```

---

## External API Integration

This project consumes the public Gutendex API:

https://gutendex.com/

Examples of the consumed endpoints:

```http
GET https://gutendex.com/books/84/
```

```http
GET https://gutendex.com/books/?search=frankenstein
```

---

## API Specification

The API contract is defined using **OpenAPI 3.0.3**.

Source file:

```text
src/main/resources/openapi/books-api.yaml
```

Controllers and DTOs are generated automatically using the OpenAPI Generator Maven Plugin.

---

## Running the Application

### Prerequisites

* Java 21
* Maven 3.9+

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8081
```

---

## API Documentation

Swagger UI is available at:

```text
http://localhost:8081/swagger-ui/index.html
```

OpenAPI definition:

```text
http://localhost:8081/v3/api-docs
```

---

## Endpoints

### Search books

Search books using a text query.

```http
GET /books?query={text}
```

#### Example

```http
GET http://localhost:8081/books?query=frankenstein
```

#### Response

```json
[
  {
    "id": 84,
    "title": "Frankenstein; or, the modern prometheus",
    "subjects": [
      "Horror tales",
      "Science fiction"
    ],
    "authors": [
      {
        "name": "Mary Wollstonecraft Shelley"
      }
    ]
  }
]
```

---

### Get book by ID

Retrieve a book using its Gutenberg identifier.

```http
GET /books/{id}
```

#### Example

```http
GET http://localhost:8081/books/84
```

#### Response

```json
{
  "id": 84,
  "title": "Frankenstein; or, the modern prometheus",
  "subjects": [
    "Horror tales",
    "Science fiction"
  ],
  "authors": [
    {
      "name": "Mary Wollstonecraft Shelley"
    }
  ]
}
```

---

## Response Model

### BookResponse

| Field    | Type                 | Description               |
| -------- | -------------------- | ------------------------- |
| id       | Long                 | Gutenberg book identifier |
| title    | String               | Book title                |
| subjects | List<String>         | Book subjects             |
| authors  | List<AuthorResponse> | Book authors              |

### AuthorResponse

| Field | Type   | Description |
| ----- | ------ | ----------- |
| name  | String | Author name |

---

## HTTP Status Codes

| Status Code | Description                    |
| ----------- | ------------------------------ |
| 200         | Request completed successfully |
| 400         | Invalid request parameters     |
| 404         | Book not found                 |

---

## Testing

The project includes unit tests using:

* JUnit 5
* Mockito
* AssertJ

Test coverage includes:

* REST controllers
* Services
* Repository adapters
* Gutendex client
* MapStruct mappers

Run tests with:

```bash
mvn test
```

Generate coverage reports from your IDE or CI pipeline.

---

## Project Structure

```text
src/main/java/com/project/books_api

├── domain
│   ├── entity
│   └── repository
│
├── service
│   └── impl
│
├── infrastructure
│   └── gutendex
│
├── mapper
│
├── web
│   ├── api
│   ├── controller
│   └── dto
│
└── config
```

---

## Future Improvements

* Integration tests
* Caching for external API calls
* Global exception handling
* Custom error responses
* Pagination support
* Resilience patterns (timeouts, retries, circuit breakers)

---

## Author

Developed by Samuel Matas.
