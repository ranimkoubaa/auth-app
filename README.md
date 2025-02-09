# AuthApp - Authentication Application

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Prerequisites](#prerequisites)
4. [Installation](#installation)
5. [Configuration](#configuration)

---

## Introduction

AuthApp is a Spring Boot-based authentication application that provides user registration, login with JWT token generation, password reset via email, and protected resource access using role-based authorization (`USER`, `ADMIN`). It uses MySQL as the database and Hibernate for ORM.

---

## Features

- **User Registration**: Allows new users to register with a username, email, and password.
- **Login with JWT**: Authenticates users and generates a JWT token upon successful login.
- **Password Reset**: 
  - Sends a password reset email with a unique token.
  - Allows users to confirm the password reset and update their password.
- **Protected Resources**: Secures endpoints based on user roles (`USER`, `ADMIN`).
- **Logging**: Includes detailed logs for debugging and monitoring.

---

## Prerequisites

Before starting, ensure you have the following installed:

- **Java**: JDK 20 or higher.
- **Maven**: For dependency management and building the project.
- **MySQL Database**: A local or remote MySQL server.
- **Mail Service**: Gmail with an app-specific password or Mailtrap for testing.
- **Postman/CURL**: To test API endpoints.

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/auth-app.git
   cd auth-app
2. Build the Project
Run the following command to build the project:
 ```bash
    mvn clean install
 ```
3. Configure the Application
Edit the application.properties file located in src/main/resources to configure the necessary parameters.
Example Configuration :
 ```bash
    # Server Configuration
    server.port=8080
    
    # Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    
    # Email Configuration (Gmail Example)
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=your_email@gmail.com
    spring.mail.password=your_app_specific_password
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    
    # JWT Configuration
    jwt.secret=your_jwt_secret_key
    # Token expiration time in milliseconds (1 day)
    jwt.expiration=86400000 
    
    # Logging Configuration
    logging.level.org.springframework.security=DEBUG
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
 ```
