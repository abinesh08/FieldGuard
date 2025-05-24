# 🛡️ FieldGuard

**FieldGuard** is a smart field worker monitoring and management system inspired by the NREGA Mobile Monitoring concept. This backend application is built using **Java Modulith Architecture**, **Spring Boot**, **Spring Data JPA**, and **MySQL**, **Spring Security**, and is version-controlled via **GitHub**.

---

## 🚀 Features (Phase 1)

- 👷 **Worker Management**
  - Create, view, update, and delete field workers
  - Store worker details like name, gender, contact info

- 📂 **Document Management**
  - Upload and store worker ID proofs and job cards
  - Easy retrieval for field officers

- 🏗️ **Project Tracking**
  - Manage multiple field projects with assigned workers

- 🔐 **User Module**
  - Admin and supervisor roles
  - Login/register functionality (JWT planned)
 
- 🧑‍✈️ **Officer-Based Worker Access** ✅
  - Officers can retrieve worker lists based on their role and assigned location (Sub-Taluk, Taluk, District, or State)  
  - Implemented using **Modulith architecture** — no direct access between modules  
  - Communication done via **Spring Events** and shared **DTOs**

  - 📍 **GPS and Manual Attendance**
- Workers can mark attendance using **GPS auto-location detection**
- Manual attendance option available for offline/remote areas
- Supervisors can validate and manage attendance entries

---

## 🧱 Tech Stack

| Layer | Tools |
|-------|-------|
| Language | Java 21 |
| Framework | Spring Boot |
| Architecture | Java Modulith (modular monolith) |
| Database | MySQL |
| ORM | Spring Data JPA, Spring Security|
| Dev Tools | IntelliJ IDEA, Git, GitHub |
| Build Tool | Maven |


---

