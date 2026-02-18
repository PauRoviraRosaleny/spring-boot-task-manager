![Java 25](https://img.shields.io/badge/Java-25-orange?logo=java)
![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3-brightgreen?logo=springboot)

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

## 🧪 Quality & Testing
The business logic is fully covered by a professional suite of unit tests.
* **Coverage:** 100% of `TaskService`.
* **Techniques used:** Static Mocking for SecurityContext, Factory Methods for clean test data, and Exception Testing.

## 📋 Prerequisites

- Java 25 or higher.

## 🚀 Getting Started

1. **Clone the repository:**

   ```
   git clone https://github.com/PauRoviraRosaleny/spring-boot-task-manager.git
   cd spring-boot-task-manager
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

<img width="1920" height="911" alt="chrome_qicrZaQl6m" src="https://github.com/user-attachments/assets/b130d74a-a847-41d2-bf71-3af75c19136f" />

<img width="1920" height="911" alt="chrome_a05SkC4xSG" src="https://github.com/user-attachments/assets/b45f16d7-4953-4342-9f2b-0e93fd47c105" />

<img width="1920" height="911" alt="chrome_VnJejyP6Py" src="https://github.com/user-attachments/assets/0f165a2a-30cf-4f4d-a96a-a12475512947" />


## ✒️ Author

* **Pau Rovira Rosaleny** - [LinkedIn](https://www.linkedin.com/in/pau-rovira-rosaleny-142448308/) - [GitHub](https://github.com/PauRoviraRosaleny)
