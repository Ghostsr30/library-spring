📚 Library Spring

A RESTful API for a library management system, built with Java and Spring Boot. Started as a way to practice JPA relationships, and evolved into a full-featured API with layered architecture, DTOs, exception handling, and JWT-based authentication with role-based authorization.

💡 About the project

This project began as a training ground for Spring Data JPA / Hibernate relationships, and grew into a more complete backend application covering:

Entity relationships — @ManyToOne, @ManyToMany, and an association entity (Loan) that couldn't be modeled as a simple join table since it carries its own data
Layered architecture — Repository → Service → Resource, with a clear separation of responsibilities
DTO pattern — request and response DTOs separate the API's public contract from the JPA entities, avoiding infinite serialization loops and preventing clients from controlling fields they shouldn't (like a user's role on registration)
Authentication & authorization — JWT-based login, password hashing with BCrypt, and role-based access control (USER / ADMIN)
Exception handling — custom exceptions for common scenarios (resource not found, deleting an entity with dependent records)
Bean Validation — request DTOs validate incoming data before it reaches the service layer

🛠️ Technologies
Java
Spring Boot
Spring Data JPA / Hibernate
Spring Security + JWT
Bean Validation
MySQL
Maven

🗂️ Entities
Entity	Description
Author	Book author (name, nationality)
Book	Book (title, publication year) — belongs to one Author and can have several Category entries
Category	Book category (e.g. Fantasy, Romance)
User	Library user (name, email, password, roles)
Loan	Represents a loan event, linking a User to a Book, with loan date, expected return date, and actual return date
Role	Authorization role (USER or ADMIN) assigned to a User

🔗 Relationships
Book → Author: many books can belong to the same author (@ManyToOne)
Book ↔ Category: a book can have several categories, and a category can belong to several books (@ManyToMany, with a join table)
Loan → Book / Loan → User: each loan references one book and one user (two @ManyToOne relationships), representing a real-world lending event that needed its own entity to carry loan/return dates
User ↔ Role: a user can hold one or more roles (@ManyToMany)

🔐 Authentication & Authorization

The API uses JWT for stateless authentication:

A new user registers via POST /auth/register — the password is hashed with BCrypt before being stored, and the account is always assigned the USER role by default (role escalation is never accepted from client input)
A user logs in via POST /auth/login, receiving a signed JWT token
The token must be sent on subsequent requests in the Authorization: Bearer <token> header
Routes are protected based on role: read operations (GET) require authentication, while write operations (POST/PUT/DELETE) require the ADMIN role

🚀 Running the project
bash
# Clone the repository
git clone https://github.com/Ghostsr30/library-spring.git

# Enter the project folder
cd library-spring

# Run with Maven Wrapper
./mvnw spring-boot:run

The application will start on http://localhost:8080.

Database

The project uses MySQL. Create a database and set the following environment variables before running:

DB_PASSWORD=your_mysql_password
JWT_SECRET=your_jwt_secret_key

The connection details (URL, username) are configured in application.properties.

Test data

On startup (dev profile), the application seeds the database with sample authors, categories, books, users, roles, and loans, so the API can be explored right away.

📌 Main endpoints
Method	Endpoint	Description	Access

POST	/auth/register	Register a new user	Public

POST	/auth/login	Log in and receive a JWT token	Public

GET	/books	List all books	Authenticated

GET	/books/{id}	Get a book by id	Authenticated

POST	/books	Create a new book	Admin

PUT	/books/{id}	Update a book	Admin

DELETE	/books/{id}	Delete a book	Admin

GET	/authors, /categories, /users, /loans	List resources	Authenticated

(Same read/write access pattern applies across the other entities.)

🧩 What I learned building this
The practical difference between @ManyToOne, @ManyToMany, and when a relationship needs to become its own entity instead of a simple join table
Why exposing JPA entities directly through a REST API is a bad idea, and how the DTO pattern solves both the infinite-loop serialization problem and unwanted client control over sensitive fields
How JWT authentication actually works under the hood — token structure, signing, and why a stateless API shouldn't rely on server-side sessions
The difference between authentication ("who are you") and authorization ("what can you do"), and how to enforce both with Spring Security
Why passwords must be hashed (never encrypted) and how BCrypt's salting protects against rainbow-table attacks
Replacing unsafe Optional.get() calls with proper exception handling (orElseThrow), and using specific exception types instead of generic catch (Exception e) blocks
Keeping secrets (database password, JWT signing key) out of version control using environment variables

Built by Luan Vedovoto as a portfolio project.
