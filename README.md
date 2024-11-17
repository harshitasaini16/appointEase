# Appointment Booking System

This is a full-stack application built with **React** for the frontend and **Spring Boot** for the backend. The application allows users to book appointments, manage their details, and verify their booking via email OTP. It also includes an admin portal with JWT-based authentication, enabling secure access to view and manage appointment data.

---

## Features

### User Features

- **Appointment Booking**:  
  Users can book an appointment by selecting the date, time, and providing the required details.  
  - A confirmation email with OTP is sent for verification.
  - Upon successful OTP verification, a confirmation email is sent for the booking.

- **Manage Details**:  
  - Add personal information while booking an appointment.
  - Edit details for any upcoming appointment before final confirmation.

### Admin Features

- **Admin Login**:  
  - Secure login for admin users.
  - API access requires a valid JWT token for authentication.

- **View Appointments**:  
  - Admin can view the details of all bookings, including user-provided information and appointment schedules.

---

## Technologies Used

### Frontend
- **React**  
  - Functional components for building a responsive and dynamic UI.
  - Axios for making API calls.
  - Form validation using React hooks and custom logic.
  - CSS for styling the application.

### Backend
- **Spring Boot**  
  - REST APIs for handling user and admin operations.
  - JWT-based authentication for secure admin access.
  - Email service for OTP and booking confirmations.
  - Hibernate and JPA for database interactions.

### Database
- **MySQL**  
  - Relational database for storing user details, appointment data, and admin credentials.

---

## Key Functionalities

1. **User Workflow**:
   - Book an appointment by selecting date, time, and filling out traveler details.
   - Receive an OTP on the provided email for verification.
   - Enter the OTP to confirm the booking.
   - Get a confirmation email with the booking details.

2. **Admin Workflow**:
   - Admin logs in with valid credentials to access the admin panel.
   - Secure API access with JWT token validation.
   - View the complete list of appointments and user details.

---

## Setup Instructions

### Prerequisites
- **Node.js** for the frontend.
- **Java (JDK 17 or above)** for the backend.
- **MySQL** for the database.

---

### Backend Setup

1. Clone the backend repository:
   ```bash
   git clone <backend-repo-url>
   cd <backend-folder>

2. Configure application.properties in the src/main/resources folder:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/appointmentdb
    spring.datasource.username=root
    spring.datasource.password=<password>
    spring.mail.username=<your-email>
    spring.mail.password=<your-email-password>

3. Run the backend server:
   ```bash
   mvn spring-boot:run


### Frontend Setup

1. Clone the frontend repository:
   ```bash
   git clone <frontend-repo-url>
    cd <frontend-folder>


2. Install dependencies:
   ```bash
   npm install

3. Start the frontend development server:
   ```bash
   npm start

Access the application in your browser at http://localhost:3000.

This is the Api Collection:
Appointment Booking API Collection
Appointment Booking API Collection
<img width="740" alt="Screenshot 2024-11-17 at 11 45 10 AM" src="https://github.com/user-attachments/assets/56c29a5a-249b-4ede-be4e-47624b7634c3">
<img width="752" alt="Screenshot 2024-11-17 at 11 45 21 AM" src="https://github.com/user-attachments/assets/49a3b7a3-785e-4077-b471-f8218a20d290">
<img width="750" alt="Screenshot 2024-11-17 at 11 45 31 AM" src="https://github.com/user-attachments/assets/249dc70f-d1b9-443a-ae63-db8a14c14704">
<img width="731" alt="Screenshot 2024-11-17 at 11 45 40 AM" src="https://github.com/user-attachments/assets/7dcc02a8-4414-49e5-bd42-39401d8aff45">
<img width="751" alt="Screenshot 2024-11-17 at 11 45 49 AM" src="https://github.com/user-attachments/assets/0edd8ba7-e78b-4582-9926-859d3b588233">
<img width="748" alt="Screenshot 2024-11-17 at 11 45 58 AM" src="https://github.com/user-attachments/assets/82f8b204-f953-4761-9d96-672bbf2972af">
<img width="750" alt="Screenshot 2024-11-17 at 11 46 05 AM" src="https://github.com/user-attachments/assets/d78ffb48-cbb7-4e07-b549-33a1303a932b">
<img width="743" alt="Screenshot 2024-11-17 at 11 46 12 AM" src="https://github.com/user-attachments/assets/e06949a3-3ae3-4b10-9b0b-6c39e8d87545">
<img width="763" alt="Screenshot 2024-11-17 at 11 46 22 AM" src="https://github.com/user-attachments/assets/af1ce8df-831b-4457-9cce-c68ef7eae093">
<img width="747" alt="Screenshot 2024-11-17 at 11 46 29 AM" src="https://github.com/user-attachments/assets/5b15a5a7-a89e-4d40-9a60-df032644bcd1">
<img width="750" alt="Screenshot 2024-11-17 at 11 46 35 AM" src="https://github.com/user-attachments/assets/88cb2f51-09d5-4ce3-ba23-796e7d88d346">









