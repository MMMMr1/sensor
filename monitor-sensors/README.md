# Description
Service for managing and displaying sensors, sensor types and units, including user registration and authorization.


### Prerequisites
- Java 21

### Technology Stack

#### Backend:
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Mapstruct
- Liquibase
- Docker

#### Database
- Postgres

#### Documentation
- Swagger (OpenAPI 3.0)


## Usage

Swagger:  http://localhost:8080/swagger-ui/index.html#/

 
Example API Requests: 
#### Registration and authorization

1. **Register user:**
```bash
curl --location 'localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data '{"username":"admin",  "password": "admin"}'
``` 

2. **Login:**
```bash
curl --location 'localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{"username":"admin",  "password": "admin"}'
``` 

#### Sensors

1. **Save sensor:**

```bash
curl --location 'localhost:8080/sensors' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJhZG1pbiIsImlhdCI' \
--data '{
    "name": "Ba321",
    "model": "ac-23",
    "range": {
        "from": 22,
        "to": 45
    },
    "type": 1
}'
``` 

2. **Get sensors:**

```bash
curl --location --request GET 'localhost:8080/sensors/admin' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJhZG1pbiIsImlhdCI' \
--data ' '
```

```bash
curl --location --request GET 'localhost:8080/sensors' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJhZG1pbiIsImlhdCp' \
--data ' '
``` 
2. **Search by 'name' or 'model' sensors:**

```bash
curl --location --request GET 'localhost:8080/sensors/search' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJnamhnMSIsImlhdCI' \
--data ' {
    "name": "14",
    "model": "23"
 }'
``` 

3. **Update sensor:**

```bash 
curl --location --request PUT 'localhost:8080/sensors/20' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJnamhnMiIsImlhdCI' \
--data '{
    "name": "Barometer14",
    "model": "ac-23",
    "range": {
        "from": 22,
        "to": 45
    },
    "type": 1,
    "unit": 1,
    "location": "kitchen",
    "description": "description"
}'
``` 
4. **Delete sensor:**

```bash
curl --location --request DELETE 'localhost:8080/sensors/14' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJhZG1pbiIsImlhdCI' \
--data ''
``` 
#### Sensor types (CRUD)
 
1. **Get sensor types:**

```bash
curl --location 'localhost:8080/types' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJ1c2VyIiwiaWF0Ijo'
```

```bash
curl --location 'localhost:8080/types/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJ1c2VyIiwiaWF0Ij'
```
#### Sensor types (CRUD)

1. **Get units:**

```bash
curl --location 'localhost:8080/units' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJ1c2VyIiwiaWF0Ijo'
```

```bash
curl --location 'localhost:8080/units/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdTZWN1cml0eUV4YW1wbGUiLCJzdWIiOiJ1c2VyIiwiaWF0Ij'
```



### Installation

**Clone the repository:**
```bash
git https://github.com/MMMMr1/sensor.git
```

