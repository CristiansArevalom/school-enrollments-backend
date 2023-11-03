# API REST FOR SCHOOL ENROMNETS
This project is a RESTful API for managing student enrollment records at an academy. It utilizes Java, Spring Boot, JPA as an ORM with PostgreSQL, authentication with JWT, and testing with JUnit and Mockito. The project is structured based on the MVC model.

This project was developed for the program "Java backend developer"

## Data Model
    Data model based on this relational model
![Relational Data Model](/assets/ERD.JPG)
## Tech Stack 💻

- [Java](https://www.java.com/es/)
- [Postgresql](https://www.postgresql.org)
- [JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Booot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)
- [ModelMapper](https://modelmapper.org/)
- [Mockito](https://site.mockito.org/)

## Installation and Running App :zap:



**1. Clone this repo by running the following command :-**


```bash
git clone https://github.com/CristiansArevalom/school-enrollments-backend.git
cd school
```


**2. Install the required package:**

Remember that the application.properties file must be created for the API to function properly.

The project is created with Maven, so all you need to do is import it into your IDE and build the project to resolve the dependencies. However, you should configure the PostgreSQL connection in the application.properties file.

It is necessary to specify the profile in your properties file, and avoid using the 'default' profile if you want to run tests and implement authentication in the development environment.

Finally, it is necessary to specify the jwt.secret that Spring Boot security and JWT will use.



```bash
Create a PostgreSQL database with the name school and add the credentials to /resources/application.properties.
The default ones are :

spring.profiles.active=dev
 
spring.jpa.database=postgresql
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update

spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect  //<Spring Boot 3.0.x
spring.datasource.url=jdbc:postgresql://localhost/school
spring.datasource.username=postgres
spring.datasource.password=123
jwt.secret=aEIu9S7cvZUnPJWezau3rKUCxj4BLtpCVzhVSyam93prJOxofs7688P0OD5tmTIsLL6u7G9HpXvT

```

**3. Now run the mvn spring-boot:run command to start the project :-**


```bash
mvn spring-boot:run
```


**4.** **🎉 Open Postman and test the restAPI on this url: `https://127.0.0.1:8080`**

**Follow the steps of the Steps presentation in the repository -**


## Features and Functionalities


- Crud operations for students, course, enrollments, update enrollment details ( course on enrollments).
- Read and create roles.
- Read, create user, and assing role to user.
- login endpoint with user and password using jwt

- SQL for database: Relational database PostgreSQL

# Available Endpoints:
- [Login](#login)
  - [POST] /login

> Note: All the above endpoints require the JWT bearer token generated after login. This can be changed in the SecurityFilterChain filterChain method inside the WebSecurityConfig java file
- [Students](#students)
    - [GET] /api/students
    - [GET] /api/students/sortedByAge
    - [POST] /api/students
    - [PUT] /api/students/:id
    - [DELETE] /api/students/:id

- [Courses](#courses)
    - [GET] /api/courses
    - [POST] /api/courses
    - [PUT] /api/courses/:id
    - [DELETE] /api/courses/:id

- [Enrollments](#enrollments)
    - [GET] /api/enrollments
    - [GET] /api/enrollments/studentsByCourse
    - [POST] /api/enrollments
    - [PUT] /api/enrollments/:id
    - [DELETE] /api/enrollments/:id
    - [PUT] /enrollmentDetail?courseRoom=:courseRoom&idEnrollmentDetail=:idEnrollmentDetail
  
      - Example:/api/enrollment/enrollmentDetail?courseRoom=testEdit2&idEnrollmentDetail=13

- [User](#user)
    - [GET] /api/users
    - [POST] /api/users
    - [POST] api/users/assingRole?userId=:idUser&roleId=:idRole

- [Roles](#roles)
  - [GET] /api/roles
  - [POST] /api/roles


# Authors
- [@CristiansArevalom](https://github.com/CristiansArevalom)

