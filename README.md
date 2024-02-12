# GoalGrind - Task Management Application

Welcome to the **Task Management Application** Repository. This web application is designed to help users manage their tasks efficiently.

*It consists of a **Spring Boot** backend for handling data operations and an **Angular** Front-End for the user interface.*

## BackEnd (Spring Boot)

### Task Controller
- The *Task Controller* manages tasks and user authentication.
- It provides endpoints for CRUP operations on tasks and user Login.

## Model Classes
- #### Task
  - Represents a task with properties like task name, description and status.

### User Repository
- The *Task Repository* interface extends *JpaRepository* to handle database operations related to tasks.

### Task Repository
-The *User Repository* interface extends *JpaRepository* to handle database operations related to users.

