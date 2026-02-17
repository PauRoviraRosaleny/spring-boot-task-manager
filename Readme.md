
# 🚀 Task Manager - Spring Boot & Security

Welcome to **Task Manager**! This is a full-stack learning project built to manage personal tasks securely. The application allows multiple users to register, log in, and manage their own private task lists.

## 🌟 Key Features

- **User Management:** Custom user registration and secure login system.
- **Robust Security:** Password encryption using **BCrypt** and route protection via **Spring Security**.
- **Data Privacy:** Full data isolation—each user can only access and manage their own tasks.
- **Task CRUD:** Create, Read, Update, and Delete tasks through an intuitive interface.
- **Responsive Design:** Built with **Thymeleaf** and **Bootstrap 5** for a seamless experience across all devices.

## 🛠️ Tech Stack

- **Backend:** Java 25, Spring Boot 4.0.2.
- **Security:** Spring Security.
- **Persistence:** Spring Data JPA / Hibernate.
- **Database:** H2 Database (In-memory for development).
- **Frontend:** Thymeleaf, HTML5, CSS3 (Bootstrap).
- **Build Tool:** Maven.

## 📋 Prerequisites

- Java 25 or higher.

## 🚀 Getting Started

1. **Clone the repository:**

   ```
   git clone https://github.com/PauRoviraRosaleny/spring-boot-task-manager.git
   cd task-manager
   ```
2. **Build the project:**
   ```
   mvn clean install
   ```
3. **Run the application:**
   ```
   mvn spring-boot:run
   ```
4. **Access the app:**
Open your browser and navigate to http://localhost:8080

🔐 Database Inspection (H2 Console)
To inspect the database while the app is running:

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data/tasksdb

User: sa

Password: (empty)

📸 Screenshots
(Pro-tip: Add screenshots of your Login page and Dashboard here to showcase your work!)

## ✒️ Author

* **Pau Rovira Rosaleny** - [LinkedIn](https://www.linkedin.com/in/pau-rovira-rosaleny-142448308/) - [GitHub](https://github.com/PauRoviraRosaleny)