# CrudServer

A **learning project**: building a CRUD HTTP server with core Java, without a web framework.

The server uses only the JDK's built-in `com.sun.net.httpserver.HttpServer`, plain JDBC and virtual threads. It is not production code. The goal is to understand what frameworks do under the hood: routing, handlers, JSON, database access.

## Stack

- Java 21
- Maven
- PostgreSQL (JDBC driver)
- Jackson (JSON)
- JUnit 5

## Status

| Endpoint        | Method | Description                                  |
|-----------------|--------|----------------------------------------------|
| `/`             | GET    | Health message in JSON                       |
| `/language`     | GET    | List all rows of the `language` table        |
| `/favicon.ico`  | GET    | Server icon                                  |

Create, update and delete are not implemented yet.

## Project structure

```
src/main/java/be/hyf/
├── App.java          # server setup and context routing
├── entities/         # data model (Language)
├── handlers/         # one HttpHandler per route
└── helpers/          # DB connection, JSON message types
conf/db.properties    # local DB settings (not committed)
```

## Data

The data comes from the Sakila sample database, loaded from [oleg578/sakiladb-postgres](https://github.com/oleg578/sakiladb-postgres), a fork of [sakiladb/postgres](https://github.com/sakiladb/postgres).

Sakila models a DVD rental store: films, actors, categories, languages, inventory, customers, rentals and payments. The repository packages it as a ready-to-run PostgreSQL image, which makes it a convenient source of realistic relational data for CRUD practice. The server currently uses only the `language` table.

## Configuration

Create `conf/db.properties` (ignored by git):

```properties
db.url=jdbc:postgresql://localhost:5432/<database>
db.user=<user>
db.password=<password>
```

## Run

```bash
mvn compile
java -cp target/classes:$(mvn -q dependency:build-classpath -Dmdep.outputFile=/dev/stdout) be.hyf.App
```

Or run `be.hyf.App` from IntelliJ. The server listens on <http://localhost:8080>.

## Test

```bash
mvn test
```
