# Social Network API

The backend RESTful API service for a social networking platform. This system is designed to handle user interactions, real-time notifications, and secure media processing.

**Frontend Repository:** https://github.com/tlong1312/network-app

## System Architecture & Core Features

* **Real-time Communication:** Implemented Spring WebSocket with the STOMP protocol to handle real-time notifications for user interactions (likes, comments, friend requests). Engineered a custom JWT Interceptor to ensure secure connection handshakes.
* **Security & Authorization:** Integrated Spring Security and JWT for stateless authentication. Designed a strict Role-Based Access Control (RBAC) system to separate User and Admin privileges.
* **Media Management:** Integrated Cloudinary for scalable, cloud-based media uploading and persistent storage of user-generated content.
* **Core Domain Workflows:** Developed robust RESTful endpoints to support user profiles, dynamic posts, nested comments, and complex friend management workflows.

## Tech Stack

* **Framework:** Java, Spring Boot
* **Security:** Spring Security, JSON Web Tokens (JWT)
* **Real-time Messaging:** Spring WebSocket, STOMP
* **Storage & Database:** Spring Data JPA, Hibernate, Cloudinary API
* **API Documentation:** Swagger / OpenAPI (Optional if applicable)

## Local Environment Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/Social-Network-Backend.git](https://github.com/your-username/Social-Network-Backend.git)
   cd Social-Network-Backend
