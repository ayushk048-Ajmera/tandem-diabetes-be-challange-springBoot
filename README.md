# Tandem Diabetes Springboot Challenge

![cloudDiagram drawio](https://user-images.githubusercontent.com/75988502/190914284-55111093-b831-4899-a686-06802def6554.png)


This Program is part of the Tandem Backend Coding Exercise

Created 3 APIs:
1. Get All Users
2. Get the User By Email address
3. Create User

### User DataBase Schema

```
{ 
    “Id”: “11ff2969-f251-46df-bfb2-cf5ec768d976”,
    “firstName”: “Matthew”, 
    “middleName”: “Decker”, 
    “lastName”: ”Lund”, 
    “phoneNumber”: “555-555-5555”, 
    “emailAddress”: “matt@awesomedomain.com” 
} 
```

## APIs List
### Get all Users
```
GET /api/users
```
**Response 200**
```
[
    { 
        “userId”: “54c4e684-0a6a-449d-b445-61ddd12ffd3d”, 
        “name”: “Matthew Decker Lund”, 
        “phoneNumber”: “555-555-5555”, 
        “emailAddress”: “matt@awesomedomain.com” 
    },
    { 
        “userId”: “54c4e684-0a6a-449d-b445-61ddd12ffd3d”, 
        “name”: “Matthew Decker Lund”, 
        “phoneNumber”: “555-555-5555”, 
        “emailAddress”: “matt@awesomedomain.com” 
    }, 
]
```

### Get User By Email Address
```
GET /api/users?email="ayush@xyt.com"
```
**Response 200**
```
    { 
        “userId”: “54c4e684-0a6a-449d-b445-61ddd12ffd3d”, 
        “name”: “Matthew Decker Lund”, 
        “phoneNumber”: “555-555-5555”, 
        “emailAddress”: “matt@awesomedomain.com” 
    }

```

### Create User
```
POST /api/users
```
**Request body**
```
    { 
        “firstName”: “Matthew”, 
        “middleName”: “Decker”, 
        “lastName”: ”Lund”, 
        “phoneNumber”: “555-555-5555”, 
        “emailAddress”: “matt@awesomedomain.com” 
    }
```
**Response 201**
```
{
  "userId": "df03d450-d8d1-4be4-b0bf-1336d5344bbf",
  "fullName": "Matthew Decker Lund",
  "phoneNumber": "555-555-5555",
  "emailAddress": "matt@awesomedomain.com"
}
```

for additional Responses: http://localhost:8080/swagger-ui/index.html

## Health check endpoint
http://localhost:8080/actuator/health


## Local setup

Step 1: Download or clone the source code from GitHub to the local machine

Step 2:  ```mvn clean install```

Step 3:  ```mvn spring-boot:run```

## Docker

Run the following command on terminal to run Docker container
```
 docker build -t backend-challenge-java .
```
```
 docker run -p 8080:8080 backend-challenge-java:latest
```

## Integration Tests:
![Tests](https://user-images.githubusercontent.com/75988502/190914518-5f117830-141f-4b8d-b963-27f025541221.png)

