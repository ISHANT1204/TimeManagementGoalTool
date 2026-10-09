
# Time Management and Goal Setting Tool

A Java desktop application for setting personal goals, managing deadlines, recording time spent on goals, and monitoring progress.

This is a student group project currently under development.

## Project Details

- **Language:** Java
- **GUI:** Java Swing
- **Database:** MySQL
- **Database Connectivity:** JDBC
- **IDE:** IntelliJ IDEA

## Current Project Structure

```text
src/
├── dao/
├── exceptions/
├── gui/
├── interfaces/
├── model/
└── service/
```

## Current Development Status

The repository currently contains the initial application structure, model classes, database-access classes, service classes, a progress-tracking interface, and initial Swing screens.

The application is still being developed. Some dashboard functions and screens remain to be completed and tested.

## Requirements

- Java JDK
- MySQL Server
- MySQL Workbench (recommended)
- IntelliJ IDEA or another Java IDE
- MySQL Connector/J JDBC driver

## Database Setup

1. Start your local MySQL Server.
2. Open MySQL Workbench and connect to your server.
3. Run the following SQL script to create the database and tables.

```sql
CREATE DATABASE IF NOT EXISTS time_management_db;

USE time_management_db;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS goals (
    goal_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    target DECIMAL(10,2) NOT NULL,
    deadline DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS time_entries (
    entry_id INT PRIMARY KEY AUTO_INCREMENT,
    goal_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    duration INT NOT NULL,
    FOREIGN KEY (goal_id) REFERENCES goals(goal_id)
);

CREATE TABLE IF NOT EXISTS goal_settings (
    setting_id INT PRIMARY KEY AUTO_INCREMENT,
    goal_type VARCHAR(50) NOT NULL,
    tracking_metric VARCHAR(50) NOT NULL
);
```

## JDBC Configuration

1. Download MySQL Connector/J from the official MySQL website.
2. Add the Connector/J JAR to the IntelliJ project libraries.
3. Configure the database password through the `DB_PASSWORD` environment variable.
4. Ensure the MySQL server is running and the database exists before starting the application.

In IntelliJ, the environment variable can be configured under **Run → Edit Configurations → Environment variables**.

Do not publish your actual database password.

## Running the Project

1. Open the project in IntelliJ IDEA.
2. Confirm that the MySQL Connector/J library is configured.
3. Create the database using the SQL script above.
4. Set the `DB_PASSWORD` environment variable.
5. Run `gui.LoginFrame` when the login screen and its dependencies are ready.

## Team

This project is being developed by a group of four students.

## Project Status

**In Progress** — features are being implemented and tested incrementally.
