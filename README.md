# Twitter-like Application

A full-stack Twitter-like application built with **Java 21 Spring Boot** backend and **Angular 17** frontend, containerized with Docker and using PostgreSQL as the database.

## 🏗️ Architecture

- **Backend**: Java 21 + Spring Boot 3.2.0 + PostgreSQL
- **Frontend**: Angular 17 + TypeScript + CSS
- **Database**: PostgreSQL 15
- **Containerization**: Docker + Docker Compose

## 📋 Features

### Backend Features
- ✅ RESTful API with CRUD operations for tweets
- ✅ Tweet entity with Author, Message, and Date
- ✅ Date storage in epoch format with formatted display (`HH:mm - DD/MM/YYYY GMT`)
- ✅ PostgreSQL database integration with JPA/Hibernate
- ✅ Data validation with Bean Validation
- ✅ Automatic database seeding with sample data
- ✅ CORS configuration for frontend integration
- ✅ Comprehensive error handling
- ✅ RESTful endpoints following best practices

### Frontend Features
- ✅ Angular 17 with standalone components
- ✅ Tweet creation form with real-time validation
- ✅ Tweet listing with advanced sorting:
  - Reverse chronological order (newest first)
  - Alphabetical by author for same-date tweets
- ✅ Responsive design with Twitter-like UI
- ✅ HTTP service integration with error handling
- ✅ Character count for tweet messages (280 limit)
- ✅ Loading states and error messages

## 🚀 Quick Start with Docker

### Prerequisites
- Docker and Docker Compose installed

### Running the Application

1. **Clone and navigate to the project:**
   ```bash
   git clone <repository-url>
   cd onetag-user-console-curation
   ```

2. **Start all services:**
   ```bash
   docker-compose up --build
   ```

3. **Access the application:**
   - **Frontend**: http://localhost:4200
   - **Backend API**: http://localhost:8080/api/tweets
   - **Database**: localhost:5432 (postgres/postgres)

4. **Stop the application:**
   ```bash
   docker-compose down
   ```

## 🛠️ Local Development Setup

### Backend Setup (Java 21 + Spring Boot)

1. **Prerequisites:**
   - Java 21
   - Maven 3.9+
   - PostgreSQL 15+ (or use Docker)

2. **Database Setup:**
   ```bash
   # Using Docker for PostgreSQL
   docker run --name twitter-postgres -e POSTGRES_DB=twitter_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15-alpine
   ```

3. **Run Backend:**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

   The backend will be available at: http://localhost:8080

### Frontend Setup (Angular 18)

1. **Prerequisites:**
   - Node.js 18+ 
   - npm or yarn

2. **Install dependencies:**
   ```bash
   cd frontend
   npm install
   ```

3. **Run Frontend:**
   ```bash
   npm start
   ```

   The frontend will be available at: http://localhost:4200

## 📖 API Documentation

### Base URL
```
http://localhost:8080/api/tweets
```

### Endpoints

#### GET `/api/tweets`
Get all tweets
- **Response**: Array of Tweet objects
- **Example**:
  ```json
  [
    {
      "id": 1,
      "author": "John Doe",
      "message": "Hello World!",
      "date": "14:30 - 22/09/2025 GMT"
    }
  ]
  ```

#### POST `/api/tweets`
Create a new tweet
- **Request Body**:
  ```json
  {
    "author": "John Doe",
    "message": "Hello World!"
  }
  ```
- **Response**: Created Tweet object
- **Validation**:
  - `author`: Required, max 50 characters
  - `message`: Required, max 280 characters

#### GET `/api/tweets/{id}`
Get tweet by ID
- **Response**: Tweet object or 404 if not found

#### PUT `/api/tweets/{id}`
Update an existing tweet
- **Request Body**: Same as POST
- **Response**: Updated Tweet object or 404 if not found

#### DELETE `/api/tweets/{id}`
Delete a tweet
- **Response**: 204 No Content or 404 if not found

#### GET `/api/tweets/author/{author}`
Get tweets by author
- **Response**: Array of Tweet objects

#### GET `/api/tweets/search?q={searchText}`
Search tweets by message content
- **Response**: Array of Tweet objects

## 🏢 Project Structure

```
onetag-user-console-curation/
├── backend/                          # Spring Boot Backend
│   ├── src/main/java/com/onetag/twitter/
│   │   ├── TwitterBackendApplication.java    # Main application class
│   │   ├── entity/
│   │   │   └── Tweet.java                   # Tweet JPA entity
│   │   ├── dto/
│   │   │   ├── TweetRequestDto.java         # Request DTO
│   │   │   └── TweetResponseDto.java        # Response DTO
│   │   ├── repository/
│   │   │   └── TweetRepository.java         # JPA repository
│   │   ├── service/
│   │   │   └── TweetService.java            # Business logic
│   │   ├── controller/
│   │   │   └── TweetController.java         # REST controller
│   │   └── config/
│   │       └── DataSeeder.java              # Database seeding
│   ├── src/main/resources/
│   │   ├── application.properties           # Local config
│   │   └── application-docker.properties    # Docker config
│   ├── pom.xml                              # Maven dependencies
│   └── Dockerfile                           # Backend Docker image
├── frontend/                         # Angular Frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   │   ├── tweet-form/              # Tweet creation form
│   │   │   │   └── tweet-list/              # Tweet display list
│   │   │   ├── services/
│   │   │   │   └── tweet.service.ts         # HTTP service
│   │   │   ├── models/
│   │   │   │   └── tweet.model.ts           # TypeScript interfaces
│   │   │   ├── app.component.*              # Main app component
│   │   │   └── main.ts                      # App bootstrap
│   │   ├── styles.css                       # Global styles
│   │   └── index.html                       # Main HTML template
│   ├── package.json                         # npm dependencies
│   ├── angular.json                         # Angular CLI config
│   ├── nginx.conf                           # Nginx configuration
│   └── Dockerfile                           # Frontend Docker image
├── docker-compose.yml                # Multi-container orchestration
├── init-db.sql                       # Database initialization
└── README.md                         # This file
```

## 📊 Database Schema

### Tweet Table
```sql
CREATE TABLE tweets (
    id BIGSERIAL PRIMARY KEY,
    author VARCHAR(50) NOT NULL,
    message VARCHAR(280) NOT NULL,
    date BIGINT NOT NULL
);
```

---

**Built with ❤️ for OneTag Assignment**