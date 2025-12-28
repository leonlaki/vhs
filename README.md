# VHS task
Spring Boot REST API for managing VHS tapes, users, and rental transactions.
---
## Technologies used
- Java 21
- Spring Boot 3.5.9
- Spring Data JPA (Hibernate)
- H2 In-Memory Database
- Bean Validation (Jakarta Validation)
- Maven
---
## Features

### VHS
- Create VHS tapes
- List all VHS tapes
### Users
- Create a user
- List all users
### Rentals
- Rent a VHS tape
- Return a VHS tape
- Calculate late fees
- List all rentals
- Prevent overlapping rentals
---
## Business rules
- A VHS tape can't be rented by multiple users while that VHS is already rented
- Due dates are calculated automatically
- Late fees are calculated based on how many days passed the due date
- Return date is set on the current date
---
## How to run application
### Requirements
- Java installed
- Maven installed

### Steps
1. Clone the repository:
```bash
git clone https://github.com/leonlaki/vhs.git
cd VHS
```
2. Run the application using Maven:
```bash
mvn spring-boot:run
```
3. Application runs on http://localhost:8080
---
## H2 Console
H2 console is available at: http://localhost:8080/h2-console  
JDBC URL: jdbc:h2:mem:vhslab  
Username: sa
Password: