# Currency Converter REST API

A **Spring Boot-based REST API** that performs real-time currency conversion by integrating a third-party exchange rate service.
The application stores conversion history in a **PostgreSQL database** and includes validation, caching, logging, and monitoring features to demonstrate production-ready backend development practices.

## Features

* Real-time **currency conversion using external exchange rate API**
* **RESTful API endpoints** for creating and managing conversion records
* **PostgreSQL database integration** using Spring Data JPA
* **Caching** of exchange rates to reduce external API calls
* **Input validation** for currency codes and amounts
* **Global exception handling** for consistent API error responses
* **Structured logging** using SLF4J
* **Spring Boot Actuator** for application monitoring and health checks
* **Swagger / OpenAPI documentation** for easy API exploration

## Tech Stack

* **Java**
* **Spring Boot**
* **Spring Web (REST API)**
* **Spring Data JPA**
* **PostgreSQL**
* **Spring Boot Actuator**
* **Swagger / OpenAPI**
* **SLF4J Logging**
* **Maven**


## Project Architecture

The application follows a **layered architecture**:
controller  → Handles incoming API requests
service     → Contains business logic
repository  → Database access using Spring Data JPA
entity      → Database model
dto/model   → API response objects
config      → Configuration classes (e.g., RestTemplate)
exception   → Global exception handling


## API Endpoints

| Method | Endpoint                         | Description                         |
| ------ | -------------------------------- | ----------------------------------- |
| GET    | `/api/currency/convert`          | Convert currency using external API |
| POST   | `/api/currency/conversions`      | Save a new conversion record        |
| GET    | `/api/currency/conversions`      | Retrieve all conversions            |
| PUT    | `/api/currency/conversions/{id}` | Update conversion amount            |
| DELETE | `/api/currency/conversions/{id}` | Delete conversion record            |
| GET    | `/api/log`                       | Test application logging            |

## Example Request

### Currency Conversion

**POST**
POST /api/currency/conversions

Body:
json
{
  "fromCurrency": "USD",
  "toCurrency": "INR",
  "amount": 100
}

Response:
json
{
  "from": "USD",
  "to": "INR",
  "amount": 100,
  "rate": 82.5,
  "convertedAmount": 8250
}

## Monitoring

Spring Boot Actuator endpoints:
/actuator
/actuator/health
/actuator/info

These endpoints provide application health and runtime information.

## API Documentation

Swagger UI is available at:
http://localhost:8080/swagger-ui.html

## Running the Project

### Prerequisites

* Java 17+
* Maven
* PostgreSQL

### Steps

1. Clone the repository
git clone https://github.com/yourusername/currency-converter-api.git
2. Navigate to project directory
cd currency-converter-api
3. Configure database in `application.properties`
4. Run the application

mvn spring-boot:run


The application will start on:
http://localhost:8080


## Future Improvements

* Docker containerization
* Redis-based caching
* API rate limiting
* Authentication & authorization
* CI/CD pipeline integration

## Author

**Abhishek Vora**

Backend Developer | Java | Spring Boot
