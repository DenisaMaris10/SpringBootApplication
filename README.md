# EventsManagerSpring
## Description
  This project represents a web application which manages events of different categories and can be described through multiple tags. It also provides user authentication functionality. Users, events, event categories and tags are kept in a database.

## Features 
- **User Authentication and Authorization**
  - Only authorized users can access specific functionalities based on their roles
  - New users can register by providing informations such as their name, email address and by choosing a username and password
- **User Roles**
  - **Admins** can manage users (add and delete users) and events (add, update and remove events, event categories and tags)
  - **Users** can only view and edit events (by adding new tags)
## Technologies Used
- **Java** is the programming language used for the backend of the application
- **Spring Boot**, which provides dependency management and helps building the backend of the web application
- **Spring Security** handles authentication and authorization, ensuring that only authorized users can access specific functionalities based on their roles
- **Spring Data JPA**, used for database interaction
- **HTML** is the markup language used for the frontend of the web application
