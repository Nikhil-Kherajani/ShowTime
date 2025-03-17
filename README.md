# ShowTime - Movie Ticket Booking Application

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Next.js](https://img.shields.io/badge/Next-black?style=for-the-badge&logo=next.js&logoColor=white)](https://nextjs.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)](https://jwt.io/)
[![Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)](https://kafka.apache.org/)
[![Bucket4j](https://img.shields.io/badge/Bucket4j-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)](https://bucket4j.com/)
[![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)](https://swagger.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) <!--- Add if you have a license -->

## Overview

**ShowTime** is a full-stack movie ticket booking application, similar in concept to BookMyShow. It's built with a robust and scalable RESTful API using **Spring Boot** on the backend and a responsive, user-friendly frontend using **Next.js**. This project demonstrates a comprehensive understanding of modern web application development, including backend API design, frontend development, database management, security, and asynchronous communication.

## Features

*   **RESTful API (Backend):**
    *   Well-defined endpoints for managing users, movies, theaters, shows, and tickets.
    *   Built with Spring Boot for rapid development and easy configuration.
    *   Uses Spring Data JPA for seamless interaction with a PostgreSQL database.
    *   Implements robust error handling with custom exceptions.

*   **Secure Authentication & Authorization:**
    *   Utilizes JSON Web Tokens (JWT) and Spring Security for secure user authentication and authorization.
    *   Protects sensitive endpoints, ensuring only authenticated users can book tickets.

*   **Rate Limiting:**
    *   Implements rate limiting using Bucket4j to prevent API abuse and ensure fair usage.

*   **Asynchronous Email Notifications:**
    *   Integrates Apache Kafka to send ticket confirmation emails asynchronously.
    *   Improves API responsiveness and scalability by decoupling email sending from the main request flow.

*   **Responsive Frontend (Next.js):**
    *   Built with Next.js (a React framework) for a fast, SEO-friendly, and server-rendered frontend.
    *   Provides a user-friendly interface for browsing movies, viewing showtimes, selecting seats, and booking tickets.
    *   Responsive design ensures a seamless experience across all devices (desktops, tablets, mobile phones).
    *   Uses client-side routing for smooth navigation.
    *   Efficiently consumes the backend REST API.

*   **Database:**
    *   Uses PostgreSQL for reliable and efficient data storage.
    *   Well-designed relational database schema to ensure data integrity.

*   **API Documentation:**
    *   Provides comprehensive API documentation using Swagger UI, making it easy to understand and interact with the API.

## Technology Stack

*   **Backend:**
    *   Java 17
    *   Spring Boot 3.1+
    *   Spring Security
    *   Spring Data JPA
    *   Hibernate
    *   PostgreSQL
    *   JSON Web Tokens (JWT)
    *   io.jsonwebtoken (JJWT)
    *   Bucket4j
    *   Apache Kafka
    *   Lombok
    *   Swagger UI (springdoc-openapi-starter-webmvc-ui)

*   **Frontend:**
    *   Next.js 13+
    *   React
    *   JavaScript
    *   HTML
    *   CSS (or a CSS framework/library of your choice)

## Project Structure
showtime/
├── backend/ # Backend Spring Boot project
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ ├── com/driver/bookMyShow/
│ │ │ │ │ ├── Configurations/ # Spring configuration files (SecurityConfig, ApplicationConfig, etc.)
│ │ │ │ │ ├── Controllers/ # REST controllers
│ │ │ │ │ ├── Dtos/ # Data Transfer Objects (Request and Response DTOs)
│ │ │ │ │ │ ├── RequestDtos/
│ │ │ │ │ │ └── ResponseDtos/
│ │ │ │ │ ├── Enums/ # Enums (if any)
│ │ │ │ │ ├── Exceptions/ # Custom exception classes
│ │ │ │ │ ├── Models/ # Entity classes
│ │ │ │ │ ├── Repositories/ # JPA repository interfaces
│ │ │ │ │ ├── Services/ # Service classes (business logic)
│ │ │ │ │ └── Transformers/ # Classes for converting between entities and DTOs
│ │ │ └── resources/
│ │ │ └── application.properties # Application configuration
│ │ └── test/
│ │ └── java/
│ │ └── ... # (Tests - if you have them)
│ └── pom.xml # Maven build file
├── frontend/ # Frontend Next.js project
│ ├── components/ # React components
│ ├── pages/ # Next.js pages (routes)
│ ├── public/ # Static assets (images, etc.)
│ ├── styles/ # CSS styles
│ ├── package.json # npm/yarn dependencies and scripts
│ └── ...
├── README.md # This file
└── ...

## Setup and Installation

### Prerequisites

*   **Java 17+** (JDK 17 or later)
*   **Maven** (for building the backend)
*   **Node.js and npm/yarn** (for the frontend)
*   **PostgreSQL** (installed and running)
*   **Apache Kafka** (installed and running - optional, if you want to test email functionality)
*   **An IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Backend Setup (Spring Boot)

1.  **Clone the repository:**

    ```bash
    git clone <your-repository-url>
    cd showtime/backend
    ```

2.  **Configure Database:**
    *   Create a PostgreSQL database named `bookmyshow` (or your preferred name).
    *   Update the `application.properties` file in `backend/src/main/resources` with your database credentials:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/bookmyshow
    spring.datasource.username=your_postgres_user
    spring.datasource.password=your_postgres_password
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.hibernate.ddl-auto=update  # Use 'update' in development; 'validate' or 'none' in production.
    spring.jpa.show-sql=true # Optional: for debugging
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

    # JWT Configuration
    application.security.jwt.secret-key=YOUR_STRONG_SECRET_KEY # !!! Replace with a VERY strong, random key !!!
    application.security.jwt.expiration=86400000 # 24 hours in milliseconds

    # Kafka Configuration (Optional)
    # spring.kafka.bootstrap-servers=localhost:9092

    # Mail Configuration (Optional - if you want to set up email sending)
    # spring.mail.host=smtp.gmail.com
    # spring.mail.port=587
    # spring.mail.username=your_email@gmail.com
    # spring.mail.password=your_app_password  # Use an App Password, NOT your regular password
    # spring.mail.properties.mail.smtp.auth=true
    # spring.mail.properties.mail.smtp.starttls.enable=true
    ```
    *   **Important:**  Replace `your_postgres_user`, `your_postgres_password`, `YOUR_STRONG_SECRET_KEY` with your actual PostgreSQL credentials, and a strong secret key for JWT.  *Never* commit your real secret key to version control.
    *    For mail configuration, replace the placeholder values with real value.

3.  **Build and Run:**
    *   Open a terminal in the `backend` directory.
    *   Run:

    ```bash
    mvn clean install  # Build the project (downloads dependencies)
    mvn spring-boot:run  # Run the Spring Boot application
    ```

    *   The backend application will start, typically on port 8080.  You can access the Swagger UI at `http://localhost:8080/swagger-ui/index.html`.

### Frontend Setup (Next.js)

1.  **Navigate to the frontend directory:**

    ```bash
    cd ../frontend
    ```

2.  **Install Dependencies:**

    ```bash
    npm install  # Or: yarn install
    ```

3.  **Configure API Base URL (Important):**

    *   Create a `.env.local` file in the `frontend` directory (at the root of the frontend project).
    *   Add the following line, adjusting the URL if your backend is running on a different port or host:

    ```
    NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
    ```
    *   **Why `.env.local`?** Next.js has built-in support for environment variables.  `.env.local` is the standard file for local development environment variables.  The `NEXT_PUBLIC_` prefix is crucial; it makes the variable available to your client-side code.

    *   **Access the variable in your code:**  You can access this environment variable in your Next.js components using `process.env.NEXT_PUBLIC_API_BASE_URL`.  For example, when making API requests:

    ```javascript
    // In a Next.js component (e.g., pages/movies.js)
    async function fetchMovies() {
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_BASE_URL}/movie/all`);
      const data = await res.json();
      return data;
    }
    ```

4.  **Run the Development Server:**

    ```bash
    npm run dev  # Or: yarn dev
    ```

    *   The Next.js development server will start, typically on port 3000. You can access the frontend at `http://localhost:3000`.

### Running the Full Application

1.  **Start the backend** (Spring Boot).
2.  **Start the frontend** (Next.js).
3.  **Access the application** through the frontend URL (usually `http://localhost:3000`).

## API Documentation (Swagger UI)

*   Once the backend is running, you can access the interactive API documentation via Swagger UI at: `http://localhost:8080/swagger-ui/index.html`

## Deployment (Optional)

*   **Backend (Spring Boot):**
    *   You can deploy the Spring Boot application as a JAR file to various platforms like Heroku, AWS (Elastic Beanstalk, EC2), Google Cloud, Azure, etc.
    *   Consider using Docker for containerization.

*   **Frontend (Next.js):**
    *   Next.js applications are best deployed to platforms like Vercel or Netlify, which provide excellent support for Next.js features (server-side rendering, static site generation, etc.).
    *   You can also deploy to other platforms that support Node.js applications.

## Contributing

Contributions are welcome! Please follow these steps:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Make your changes and commit them with clear messages.
4.  Create a pull request to merge your changes into the main branch.

## License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details. (You'll need to create a LICENSE file if you want to specify a license).


<!-- # Book My Show APIs Spring Boot Project

This project is a Spring Boot implementation of the backend APIs for a ticket booking system similar to the popular platform "BookMyShow". It provides a set of RESTful APIs that enable client applications to interact with the ticket booking system and perform various operations.

## Features
* **User Registration:** Users can create an account, log in, and manage their profile information.
* **Movie Management:** Admin users can add, edit, and remove movie from the system.
* **Theater Management:** Admin users can add, allocate seats, edit, and remove Theaters from the system.
* **Ticket Booking:** Users can browse through the available movie, select the desired event, and book tickets for it.
* **Seat Selection:** Users can choose their preferred seats from the available options for a selected event.
* **Booking History:** Users can view their booking history and check the details of their past bookings.
* **Email Notifications:** Users receive email notifications for successful bookings and important updates.
## Technologies Used
* **Java 8+**
* **Spring Boot**
* **Spring MVC**
* **Spring Data JPA**
* **MySQL (as the database)**
* **Maven (for dependency management)**
* **SMTP Server (for sending email notifications)**

## Project Working FlowChart

![BMSflowchart.jpg](src%2Fmain%2Fjava%2Fcom%2Fdriver%2FbookMyShow%2FImages%2FBMSflowchart.jpg)

## Swagger-ui Screen Shot

![Book-my-show API's.png](src%2Fmain%2Fjava%2Fcom%2Fdriver%2FbookMyShow%2FImages%2FBook-my-show%20API%27s.png)
## Getting Started
To set up the project on your local machine, follow these steps:

1. Clone the repository: `git clone https://github.com/Amit8127/Book-My-Show.git`
2. Navigate to the project directory: `cd book-my-show`
3. Configure the database settings in `application.properties` file.
4. Build the project using Maven: `mvn clean install`
5. Run the application: `mvn spring-boot:run`
6. The application will be accessible at `http://localhost:8080`.

## Database Setup
This project uses MySQL as the database. Follow these steps to set up the database:
1. Install MySQL on your local machine.
2. Create a new database named bookmyshow.
3. Update the database configuration in `application.properties` file.
## API Documentation
The API documentation for this project can be found at `http://localhost:8080/swagger-ui.html`. It provides detailed information about each API, including request/response formats and parameters.
<!-- ## Authentication
Some APIs require authentication to access. To authenticate, send a request with the user's credentials (username and password) to the `/login` API. Upon successful authentication, you will receive an access token in the response. Include this token in the Authorization header of subsequent requests as a Bearer token. -->
## Acknowledgments
* **Spring Boot**
* **Postgres**
## Contributing
Contributions to the project are welcome. If you find any bugs or have suggestions for improvement, please open an issue or submit a pull request. -->