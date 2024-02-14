# Album Microservice

This microservice is designed to provide a flexible and modular solution for fetching albums and photos from an external API. It follows the principles of the hexagonal architecture to enhance maintainability, testability, and extensibility.

## Technologies Used:
- Java
- Spring Boot
- RESTful API
- H2 Database (for integration tests)
- JUnit and Mockito (for testing)

## Architecture:

The microservice adopts the hexagonal architecture, also known as the ports-and-adapters pattern. This architectural style emphasizes a clear separation between the core business logic and its external dependencies. The key components include:

### Hexagonal Architecture Components:

#### Core (Inside the Hexagon):
- **Domain Model:** Represents the core business entities, such as Album and Photo.
- **AlbumService:** Implements business logic related to albums, focusing on the core functionality without being tied to specific technologies.

#### Ports:
- **AlbumRepositoryPort:** Defines the interface for storing and retrieving albums. Implemented by the infrastructure layer.

#### Adapters:
- **AlbumRepository:** Implements the AlbumRepositoryPort interface, serving as an adapter that connects the core business logic with the data store. In a real-world scenario, this could be a database or any other data storage solution.
- **AlbumApiClientPort:** Defines the interface for communicating with the external API. Implemented by the infrastructure layer.
- **ApiClient:** Implements the AlbumApiClientPort interface, acting as an adapter to fetch albums and photos from the external API.

### Controllers:
- **AlbumController:** Exposes endpoints for fetching albums.

### Tests:
The microservice includes both unit and integration tests to ensure correctness and reliability.

#### Unit Tests:
- **AlbumControllerTest:** Tests the AlbumController class.
- **AlbumServiceTest:** Tests the AlbumService class.

#### Integration Tests:
Integration tests verify the functionality of the entire system, including interactions with external APIs.

## Usage:
To use this microservice, run it as a Spring Boot application. Endpoints can be accessed via HTTP requests.

## How to Run:
1. Clone the repository.
2. Build the project using Maven.
3. Run the application using `mvn spring-boot:run` or by executing the generated JAR file.
4. Access the endpoints using the provided URLs.

## Contributors:
- Redouane Mehdi

## License:
This project is licensed under the [MIT License](LICENSE).
