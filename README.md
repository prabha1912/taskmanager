# 🗂️ Task Manager Web Application

A simple task management system for HMCTS caseworkers to efficiently create, view, update, and delete tasks. Built using **Java Spring Boot**, **Thymeleaf**, **MySQL**, and **vanilla JavaScript/CSS**.

---

## ✅ Features

- ✅ Create a new task (title, description, status, due date/time)
- ✅ View all tasks in a table
- ✅ Edit/update tasks
- ✅ Delete tasks
- ✅ Server-side validation and error handling
- ✅ Responsive and user-friendly interface with Thymeleaf templates

---

## 🧰 Technologies Used

| Layer        | Technology           |
|--------------|----------------------|
| Backend      | Java, Spring Boot, Spring MVC, JPA/Hibernate |
| Frontend     | Thymeleaf, HTML, CSS, JavaScript |
| Database     | MySQL                |
| Build Tool   | Gradle               |
| Testing      | JUnit                |

---

## 🏗️ Project Structure

src/
├── main/
│ ├── java/
│ │ └── com.example.taskmanager/
│ │ ├── controller/
│ │ ├── model/
│ │ ├── repository/
│ │ ├── service/
│ │ └── TaskmanagerApplication.java
│ └── resources/
│ ├── templates/
│ │ ├── task-form.html
│ │ └── tasks.html
│ ├── application.properties


---

## 💾 Database Configuration

In `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
🧪 Sample Task Input
Title: Submit case report

Description: Prepare and upload the court case report

Status: IN_PROGRESS

Due Date: 2025-05-30 10:00

▶️ Running the App
📦 Prerequisites
Java 17+

MySQL installed and running

IntelliJ IDEA (Community or Ultimate)

Gradle

📍 Steps
Clone the repository:

bash
Copy
Edit
git clone https://github.com/your-username/taskmanager.git
cd taskmanager
Set up MySQL database:

sql
Copy
Edit
CREATE DATABASE taskmanager_db;
Update application.properties with your DB credentials.

Run the app:

bash
Copy
Edit
./gradlew bootRun
Open your browser and go to:

bash
Copy
Edit
http://localhost:8080/tasks
🧪 Testing
Basic unit tests are located in src/test/java/.

To run tests:

bash
Copy
Edit
./gradlew test
📚 API Endpoints (If accessed via REST)
Method	Endpoint	Description
GET	/tasks	List all tasks
GET	/tasks/{id}	View task by ID
POST	/tasks/save	Create or update task
GET	/tasks/edit/{id}	Edit form for task
GET	/tasks/delete/{id}	Delete task
