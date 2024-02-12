# GoalGrind - Task Management Application

Welcome to the **Task Management Application** Repository. This web application is designed to help users manage their tasks efficiently.

*It consists of a **Spring Boot** backend for handling data operations and an **Angular** Front-End for the user interface.*

## Problem Description

**Description**: Create a web-based task management system where users can perform the following actions:
- **View Tasks**: Display a list of tasks with details such as task name, description, and status (pending, completed, etc.).
- **Add Task**: Users should be able to add new tasks by providing a task name, description, and setting an initial status. The statuses can be to-do, in-progress, blocked and done.
- **Edit Task**: Allow users to edit existing tasks, modifying details such as task name, description, and status.
- **Delete Task**: Provide the functionality to delete tasks.

**Technical Requirements**:
**Backend (Spring Boot)**:
- Use Spring Boot to create RESTful APIs for CRUD operations (Create, Read, Update, Delete).
- Create endpoints like /tasks for getting all tasks, /tasks/{id} for getting a specific task, /tasks/create for creating a new task, /tasks/update/{id} for updating a task, and /tasks/delete/{id} for deleting a task.
-Use databases like MySQL or PostgreSQL.


**Frontend (AngularJS)**:
- Create a single-page application using AngularJS.
- Implement views to display a list of tasks, an add/edit task form, and appropriate user interfaces for these actions.
- Use AJAX to communicate with the backend APIs for fetching, adding, updating, and deleting tasks.
- Implement basic form validation on the front end. Additional Considerations:
- Implement basic authentication (can be as simple as a username/password) to access the task management system.
- Apply some basic styling to make the interface visually appealing using CSS.



## Solution

### BackEnd (Spring Boot)

#### Task Controller
- The *Task Controller* manages tasks and user authentication.
- It provides endpoints for CRUP operations on tasks and user Login.

#### Model Classes
- ##### Task
  - Represents a task with properties like task name, description and status.
 
- ##### User
  - Represents a user with properties like username, password and a list of tasks.

#### User Repository
- The *Task Repository* interface extends *JpaRepository* to handle database operations related to tasks.

#### Task Repository
-The *User Repository* interface extends *JpaRepository* to handle database operations related to users.


### FrontEnd(Angular)

#### Components
- **Login Component**
  - Handles user authentication so users can log in with their credentials.
- **Details Component**
  - Displays a list of tasks for the logged-in user.
  - User Can view, update and delete tasks from this component.
- **Add Task Component**
  - Allows the Logged user to add tasks.
- **Update Task Component**
  - Allows the user to update the existing tasks.
- **View Task Component**
  - Displays detailed information about a specific task.

#### Services
- **App Logic Service**
  - Provides methods to interact with the backend API for tasks.
- **AuthService Service**
  - Provides methods for user logout.


#### Routing
Users are directed to the login page by default. After successful authentication, they can navigate to the task list, add tasks, update tasks or view task details.


##### How to run?
- start the backend first possibly in eclipse.
- navigate to the angular project and **ng serve** the application
- application should be available at the URL (`http://localhost:4200`)
  

