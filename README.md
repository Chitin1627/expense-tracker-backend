# Expense Tracker Backend
This is the backend service for the [Expense Tracker application](https://github.com/Chitin1627/expense-tracker-app), built using Spring Boot and MongoDB. The backend manages user authentication, expenses, and categories, and provides APIs for data manipulation and retrieval.

## Features
  1) JWT-based authentication
  2) Expense management (CRUD operations)
  3) User management (registration, login)
  4) Data persistence with MongoDB
  5) Token validation and session management
  6) RESTful API with JSON responses

## Tech Stack
  1) **Backend framework:** Spring Boot
  2) **NoSQL database:** MongoDB
  3) **Primary programming language:** Java
  4) **Dependency management:** Maven

## API Endpoints

### Authentication

| Method | Endpoint        | Description               | Authorization |
|--------|-----------------|---------------------------|---------------|
| POST   | `/api/users/authenticate`     | Log in user                | No            |
| POST   | `/api/users/register`  | Register a new user        | No            |
| POST   | `/api/users/validate-token` | Validates JWT Token | No |

## Users

| Method | Endpoint        | Description               | Authorization |
|--------|-----------------|---------------------------|---------------|
| GET   | `/api/users/user-details`     | Returns user details             | JWT Token            |
| PUT   | `/api/users/change-password`  | Changes password      | JWT Token            |

## Expenses
| Method | Endpoint        | Description               | Authorization |
|--------|-----------------|---------------------------|---------------|
| POST   | `/api/expenses`     | Creates an expense            | JWT Token            |
| GET    | `/api/expenses/date?startDate="YYYY-MM-DD"&endDate="YYYY-MM-DD"`  | Returns expenses between the given dates    | JWT Token            |
| GET    | `/api/expenses/user-expenses` | Returns all expenses of the user | JWT Token |
| GET    | `/api/expenses/user-expenses/current-month` | Returns all expenses of the user for the current month | JWT Token |
| GET    | `/api/expenses/user-expenses/last-six-months` | Returns all expenses of the user for the last 6 months | JWT Token |
| GET    | `/api/expenses/user-expenses/previous-month` | Returns all expenses of the user for the previous month | JWT Token |  
| GET    | `/api/expenses/user-expenses/date?date="YYYY-MM-DD"` | Returns all expenses of the user for a particular date | JWT Token | 
| DELETE    | `/api/expenses/delete?id=INTEGER` | Deletes the expenses by ID | JWT Token | 
| PUT    | `/api/expenses/edit` | Edits an expense | JWT Token |

## Category
| Method | Endpoint        | Description               | Authorization |
|--------|-----------------|---------------------------|---------------|
| GET   | `/api/categories`     | Returns all categories         | JWT Token            |
| POST   | `/api/categories`   | Creates a new category    | JWT Token            |
