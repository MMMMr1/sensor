# Description
A service designed to collect, process, and retrieve sensor statistics from the monitor-service.

Key Features:
- Data Collection: Automatically fetches sensor data daily at 02:00 from monitor-service endpoints. 
- Statistics Generation: Computes sensor insights, including total count and breakdown by type. 
- Data Storage: Saves statistical records in a dedicated database for historical tracking. 
- Flexible Queries: Allows users to retrieve statistics for any specified time range.


### Prerequisites
- Java 21

### Technology Stack

#### Backend:
- Java
- Spring Boot
- Spring Data JPA 
- Quartz Scheduler
- Spring Boot Actuator
- Mapstruct
- Liquibase
- Docker

#### Database
- Postgres

#### Documentation
- Swagger (OpenAPI 3.0)


## Usage

Swagger:  http://localhost:8081/swagger-ui/index.html#/


 
Example API Requests:

#### Statistic

1. **Get statistic:**

```bash
curl --location --request GET 'localhost:8081/statistics?start=01.12.2023&end=01.07.2025' \
--header 'Content-Type: application/json' \
--data ' '
```  


#### Quartz Scheduler

1. **To retrieve the list of registered job names:**

```bash
curl --location 'http://localhost:8081/actuator/quartz/jobs'
```  

1. **To trigger a particular Quartz job:**

```bash
curl --location 'http://localhost:8081/actuator/quartz/jobs/DEFAULT/sensorStatisticsJob' \
--header 'Content-Type: application/json' \
--data '{"state":"running"}'
```  



### Installation

**Clone the repository:**
```bash
git https://github.com/MMMMr1/sensor.git
```

