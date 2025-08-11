# Flight Booking System

A scalable, microservices-based flight booking platform built with Spring Boot.

## 🚀 Features

- **Multi-tenant Architecture**: Separate services for core operations, database, and notifications
- **Airline Management**: Register and manage airlines with admin approval workflow
- **Flight Search & Booking**: Search for flights and book tickets
- **Email Notifications**: Real-time email notifications for various events
- **RESTful APIs**: Well-documented endpoints for all operations
- **JWT Authentication**: Secure API access with JSON Web Tokens

## 🏗️ System Architecture

The system is divided into three main microservices:

1. **Central API** (`central-api`)
   - Main business logic and orchestration
   - Handles user requests and coordinates between services
   - Implements security and authentication

2. **Database API** (`db-api`)
   - Manages all database operations
   - Handles data persistence and retrieval
   - Implements data access layer

3. **Notification API** (`notification-api`)
   - Manages all email notifications
   - Handles async notification delivery
   - Supports various notification templates

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3.x
- **Database**: PostgreSQL/MySQL (configurable)
- **Authentication**: JWT (JSON Web Tokens)
- **Email**: JavaMail with SMTP
- **Build Tool**: Maven
- **API Documentation**: SpringDoc OpenAPI

## 🔧 Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL/MySQL
- SMTP server (or use a testing service like Mailtrap)

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone <repository-url>
cd flight-booking-system
```

### 2. Database Setup
1. Create a new database for each service
2. Update the `application.properties` files with your database credentials

### 3. Build the Project
```bash
# Build all services
mvn clean install
```

### 4. Run the Services
Run each service in separate terminals:

```bash
# Terminal 1 - Central API
cd central-api
mvn spring-boot:run

# Terminal 2 - DB API
cd db-api
mvn spring-boot:run

# Terminal 3 - Notification API
cd notification-api
mvn spring-boot:run
```

## 🔐 Authentication

The system uses JWT for authentication. Include the token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## 📚 API Documentation

Once the services are running, access the API documentation:
- Central API: http://localhost:8080/swagger-ui.html
- DB API: http://localhost:8081/swagger-ui.html
- Notification API: http://localhost:8082/swagger-ui.html

## 📧 Email Notifications

For development, you can use Mailtrap or a similar service. Update the email configuration in `notification-api/src/main/resources/application.properties`.

## 🔍 Debugging Tips

1. **Email Sending Issues**:
   - Check SMTP configuration
   - Verify recipient addresses in DTOs match between services
   - Ensure proper field names in DTOs (e.g., `admin` vs `appAdmin`)

2. **404 Errors**:
   - Verify endpoint mappings in controllers
   - Ensure path variables use `@PathVariable` correctly
   - Check service-to-service communication URLs

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📬 Contact

For any questions or feedback, please reach out to the project maintainers.
