# SpringBootJournal

**SpringBootJournal** is a simple journal-like application designed to explore and learn the intricacies of Java, Spring, and Spring Boot. The goal is to create a functional, secure, and user-friendly application while experimenting with modern development practices and tools.

---

### **Key Features**

- **Multi-User Support**: Each user can manage their personal journal entries.
- **Secure Authentication**: User credentials are securely stored in **MongoDB Atlas** (not in plain text) and authenticated using **Spring Security**.
- **Database Integration**: The application uses **MongoDB** as the database, with **Spring Data MongoDB** managing the connection and transactions to ensure atomicity for critical operations.
- **Role-Based Access Control**: Access to APIs is restricted based on roles (e.g., admin, user).
- **API Authentication**: APIs are protected, requiring authentication and following RESTful principles.
- **Basic Test Coverage**: Key functionalities have been tested using **JUnit**.

---

### **Available API Context Paths**

1. **`/public`**  
   Open to all users. No authentication required.

2. **`/user`**  
   Accessible with username and password authentication.

3. **`/journal`**  
   Accessible with username and password authentication. Users can only view their own journal entries.

4. **`/admin`**  
   Restricted to users with an admin role.

---

### **Planned Improvements**

1. **Frontend Integration**  
   Implement a **React-based UI** to enhance user interaction with the application.

2. **Enhanced Testing**  
   Expand test coverage to include advanced test cases (e.g., edge cases, integration tests).

3. **Search and Filtering**  
   Add the ability to search and filter journal entries by keywords, tags, or dates.

4. **Pagination and Sorting**  
   Introduce pagination and sorting for journal entries to handle large datasets efficiently.

5. **API Documentation**  
   Use tools like **Swagger** or **SpringDoc OpenAPI** to provide comprehensive API documentation.

6. **Audit Logging**  
   Implement logging for critical user actions (e.g., login, data modification) to improve security and traceability.

7. **Email Notifications**  
   Add functionality to send notifications or reminders to users for specific journal events.

8. **Deployment**  
   Deploy the application on a cloud platform like **AWS** or **Heroku** for accessibility.

---

### **Future Vision**

The goal is to create a robust journal application with modern UI, secure backend, and scalable architecture while applying best practices in software development.
