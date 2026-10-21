# Employee Management System

A RESTful backend application built with **Spring Boot** for managing employee records. It supports creating employees, listing them with **pagination and sorting**, and deleting them, using a clean layered architecture (Controller → Service → Repository).

## Features

- Add a new employee
- Get all employees with pagination and sorting
- Delete an employee by ID
- Consistent JSON response wrapper for API responses
- DTO-based design, so entities are never exposed directly

## Tech Stack

| Layer       | Technology                  |
|-------------|-----------------------------|
| Language    | Java                        |
| Framework   | Spring Boot                 |
| Persistence | Spring Data JPA (Hibernate) |
| Database    | MySQL *(or any JPA-supported DB)* |
| Utilities   | Lombok                      |
| Build Tool  | Maven                       |

## Project Structure

```
employee/                        <- repository root
└── employee/                    <- Spring Boot project
    └── src/main/java/com/employee
        ├── controller
        │   └── EmployeeController.java
        ├── dto
        │   ├── EmployeeDto.java
        │   ├── PageResponseDto.java
        │   └── ResponseDto.java
        ├── entity
        │   └── Employee.java
        ├── repository
        │   └── EmployeeRepository.java
        └── service
            └── EmployeeService.java
```

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.8+
- MySQL (or another database)
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/nijamuddinalam23/employee.git
   cd employee/employee
   ```

2. **Create the database**
   ```sql
   CREATE DATABASE employee_db;
   ```

3. **Configure the database** in `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The server starts at `http://localhost:8080`.

## API Documentation

**Base URL:** `http://localhost:8080/api/v1/employee`

| Method | Endpoint       | Description                          |
|--------|----------------|--------------------------------------|
| POST   | `/save`        | Create a new employee                |
| GET    | `/all`         | Get employees (paginated and sorted) |
| DELETE | `/delete/{id}` | Delete an employee by ID             |

### 1. Create Employee

**POST** `/api/v1/employee/save`

Request body:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "mobile": "9876543210"
}
```

Response (`201 Created`):
```json
{
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "mobile": "9876543210"
  },
  "status": 201,
  "message": "Employee saved successfully"
}
```

### 2. Get All Employees (Pagination and Sorting)

**GET** `/api/v1/employee/all`

| Query Param | Default | Description                                        |
|-------------|---------|----------------------------------------------------|
| `page`      | `0`     | Page number (starts at 0)                          |
| `size`      | `10`    | Records per page                                   |
| `sortBy`    | `id`    | Field to sort by (`id`, `name`, `email`, `mobile`) |
| `sortDir`   | `asc`   | Sort direction: `asc` or `desc`                    |

Example:
```
GET /api/v1/employee/all?page=0&size=5&sortBy=name&sortDir=desc
```

Response (`200 OK`):
```json
{
  "data": {
    "content": [
      {
        "id": 2,
        "name": "Jane Smith",
        "email": "jane.smith@example.com",
        "mobile": "9123456780"
      }
    ],
    "pageNumber": 0,
    "pageSize": 5,
    "totalElements": 1,
    "totalPages": 1,
    "last": true
  },
  "status": 200,
  "message": "Successful"
}
```

### 3. Delete Employee

**DELETE** `/api/v1/employee/delete/{id}`

Example:
```
DELETE /api/v1/employee/delete/1
```

Response (`200 OK`):
```
Employee deleted successfully
```

## Testing the API

Use [Postman](https://www.postman.com/) or `curl`:

```bash
# Create an employee
curl -X POST http://localhost:8080/api/v1/employee/save \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john.doe@example.com","mobile":"9876543210"}'

# Get employees (page 0, 5 per page, sorted by name)
curl "http://localhost:8080/api/v1/employee/all?page=0&size=5&sortBy=name&sortDir=asc"

# Delete an employee
curl -X DELETE http://localhost:8080/api/v1/employee/delete/1
```

## Author

**Nijamuddin Alam**
GitHub: [@nijamuddinalam23](https://github.com/nijamuddinalam23)
