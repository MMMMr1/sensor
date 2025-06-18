# Sensor monitoring and statistics services

## Overview
This project consists of two main services:

- **monitor-sensors** – Manages and displays sensors, sensor types, and units. Includes user registration and authentication. 
- **statistics** – Collects and processes sensor statistics daily, storing historical data for analysis.

For detailed documentation on each service, refer to their respective README files.

Docker Setup
To build and run the services using Docker, follow these steps:

1. Build projects and install the generated .jar files into the local Maven repository
```bash
mvn clean install
``` 
2. Docker build
```bash
docker-compose build
``` 
3. Start the services
```bash
docker-compose up
``` 
🚀 After running these commands, the services will be up and ready!
