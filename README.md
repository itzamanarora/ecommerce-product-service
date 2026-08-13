# ecommerce-product-service

A lightweight Java-based microservice for managing product data in an e-commerce platform. This repository provides a RESTful API to create, read, update, and delete product records, with support for pagination, filtering, and basic search.

## Features

- CRUD operations for products
- Pagination and filtering support
- JSON-based REST API
- Integration-ready: configurable datasource (e.g., PostgreSQL)
- Unit and integration test setup
- Dockerfile for containerized deployments

## Technology stack

- Language: Java
- Build tools: Maven or Gradle (examples included)
- Web: Spring Boot (recommended)
- Persistence: JPA / Hibernate with a relational database (PostgreSQL recommended)
- Testing: JUnit, Mockito
- Containerization: Docker

> Note: The exact frameworks (Spring Boot, JPA) are recommended defaults — adapt based on the project's current implementation.

## Getting started

### Prerequisites

- Java 17+ (LTS recommended)
- Maven 3.6+ or Gradle 7+
- Docker (optional, for container builds)
- PostgreSQL or another JDBC-compatible database (for production)

### Build (Maven)

1. Build and run tests:

   mvn clean package

2. Run the application (after build):

   java -jar target/ecommerce-product-service-*.jar

### Build (Gradle)

1. Build and run tests:

   ./gradlew clean build

2. Run the application (after build):

   java -jar build/libs/*-all.jar

### Run with Docker

1. Build the Docker image:

   docker build -t ecommerce-product-service:latest .

2. Run the container (example):

   docker run -e SPRING_PROFILES_ACTIVE=prod -e DATABASE_URL=jdbc:postgresql://db:5432/products -p 8080:8080 ecommerce-product-service:latest

Adjust environment variables and Docker networking according to your environment.

## Configuration

Configuration is expected via application configuration (e.g., application.properties / application.yml) or environment variables. Common settings:

- server.port - HTTP port (default 8080)
- spring.datasource.url - JDBC URL
- spring.datasource.username
- spring.datasource.password
- spring.jpa.hibernate.ddl-auto - validate/update/none

Provide a sample config file (application.yml) in src/main/resources for convenience.

## API reference (example)

Base URL: /api/v1

- GET /products
  - Query params: page, size, sort, q (search), category
  - Response: paginated list of products

- GET /products/{id}
  - Returns a single product by ID

- POST /products
  - Body: product JSON
  - Creates a new product

- PUT /products/{id}
  - Body: product JSON
  - Updates an existing product

- DELETE /products/{id}
  - Deletes a product

Example Product JSON

{
  "id": "uuid-or-id",
  "name": "Awesome T-Shirt",
  "description": "100% cotton, comfortable fit",
  "price": 19.99,
  "currency": "USD",
  "stock": 120,
  "category": "apparel",
  "createdAt": "2026-01-01T12:00:00Z",
  "updatedAt": "2026-01-02T12:00:00Z"
}

## Data model (suggested)

- Product
  - id (UUID or long)
  - name (string)
  - description (string / text)
  - price (decimal)
  - currency (string)
  - stock (integer)
  - category (string)
  - createdAt (timestamp)
  - updatedAt (timestamp)

## Persistence

Use Spring Data JPA repositories for data access. Keep migrations in a dedicated folder (Flyway or Liquibase) and include a minimal init migration to create the products table.

## Testing

- Unit tests: mvn test or ./gradlew test
- Integration tests: use Testcontainers to run a temporary PostgreSQL instance for reliable DB tests.

## Development

- Branching: follow GitFlow or trunk-based workflow depending on team preference.
- Code style: apply a formatter (Spotless / google-java-format) and run static analysis tools (SpotBugs, PMD) in CI.
- CI: add a GitHub Actions workflow for build, test, and container image publish.

## Contributing

Contributions are welcome — please open issues for bugs or feature requests and submit pull requests with a clear description of changes, tests, and relevant documentation updates.

## License

Specify your license here (e.g., MIT, Apache-2.0). If you want, add a LICENSE file at the repository root.

## Contact

Maintainer: itzamanarora

For questions or support, open an issue in this repository.
