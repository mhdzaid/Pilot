
### Back-End (`PilotAPI`)

# PilotAPI

## Overview

This project is a Spring Boot application that provides a RESTful API for controlling a robot on a 5x5 grid. The API processes commands to move the robot and returns its updated position and direction.

## Features

- RESTful API for controlling the robot
- Validation of commands
- Exception handling and meaningful error messages

## Prerequisites

- Java 11 or higher
- Maven

## Installation

1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd PilotAPI
2. Install the dependencies:
    ```sh
    mvn install

## Running the Application
```sh
    mvn spring-boot:run
```

The application will start on http://localhost:8080.

## API Endpoints
* `POST /api/robot/move`: Processes the script to move the robot.

## Example Request
```agsl
    POST /api/robot/move
    Content-Type: application/json
    
    {
      "script": "POSITION 1 3 EAST\nFORWARD 3\nWAIT\nTURNAROUND\nFORWARD 1\nRIGHT\nFORWARD 2"
    }

```

### Response

```agsl
{
  "x": 2,
  "y": 3,
  "direction": "EAST"
}

```

### Code Structure
* `src/main/java/com/robot/pilot`: Contains the main application code.
    * `controller`: Contains the REST controllers.
    * `service`: Contains the service logic.
    * `model`: Contains the data models.
    * `config`: Contains the application configuration.
    * `exception`: Contains the custom exceptions.

### Exception Handling

The application uses `@ControllerAdvice` to handle exceptions and return meaningful error messages to the client.

### Running Tests
Run the tests using Maven:

```sh
mvn test
```










