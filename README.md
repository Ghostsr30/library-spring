📚 Library Spring

A RESTful API for a library management system, built with Java and Spring Boot to practice JPA relationships, layered architecture, and exception handling.

💡 About the project

This project was built to train and consolidate concepts of Spring Data JPA / Hibernate, focusing on the three main types of entity associations:

@ManyToOne — Book ↔ Author
@ManyToMany — Book ↔ Category
Association entity — Loan, connecting User and Book, carrying its own data (loan date, expected return date, actual return date)

Besides the data modeling, the project implements a full REST API with a layered architecture (Repository → Service → Resource), complete CRUD operations, and proper exception handling for common scenarios (entity not found, deleting an entity with dependent records, etc).

🛠️ Technologies
Java
Spring Boot
Spring Data JPA / Hibernate
H2 Database (in-memory)
Maven

🗂️ Entities

Entity	Description
Author	Book author (name, nationality)
Book	Book (title, publication year) — belongs to one Author and can have several Category entries
Category	Book category (e.g. Fantasy, Romance)
User	Library user (name, email)
Loan	Represents a loan event, linking a User to a Book, with loan date, expected return date, and actual return date

🔗 Relationships

Book → Author: many books can belong to the same author (@ManyToOne)
Book ↔ Category: a book can have several categories, and a category can belong to several books (@ManyToMany, with a join table)
Loan → Book / Loan → User: each loan references one book and one user (two @ManyToOne relationships), representing a real-world lending event that couldn't be modeled as a simple many-to-many, since it needs to carry its own dates
🚀 Running the project
bash
# Clone the repository
git clone https://github.com/Ghostsr30/library-spring.git

# Enter the project folder
cd library-spring

# Run with Maven Wrapper
./mvnw spring-boot:run

The application will start on http://localhost:8080.

H2 Console

With the app running, access the in-memory database console at:

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:libraryjpa
User: sa
Password: (leave empty)
Test data

On startup (test profile), the application automatically seeds the database with sample authors, categories, books, users, and loans, so the API can be explored right away.

📌 Main endpoints

Method	Endpoint	Description
GET	/authors	List all authors
GET	/books	List all books
GET	/books/{id}	Get a book by id
GET	/categories	List all categories
GET	/users	List all users
GET	/loan	List all loans

(Same pattern of GET/POST/PUT/DELETE applies to the other entities.)

🧩 What I learned building this
The practical difference between @ManyToOne, @ManyToMany, and when a relationship needs to become its own entity instead of a simple join table
How Hibernate translates object relationships into foreign keys and join tables automatically
Structuring an application in layers (Repository, Service, Resource) and why each layer has a single responsibility
Replacing unsafe Optional.get() calls with proper exception handling (orElseThrow)
Handling delete operations safely when an entity has dependent records

Built by Luan Vedovoto as a portfolio project.
