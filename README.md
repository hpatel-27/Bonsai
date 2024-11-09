# Bonsai Project

## Introduction

Bonsai is a full-stack web application designed to manage and track workouts. It consists of a Angular frontend and a Spring Boot backend, orchestrated using Maven and Tomcat. The application allows users to view, add, and track workout routines while interacting with a MySQL database.

## Table of Contents

1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Setup](#setup)
4. [High-Level Overview](#high-level-overview)
   - [Frontend](#frontend)
   - [Backend](#backend)
5. [Project Structure](#project-structure)
6. [Database Initialization](#database-initialization)
7. [Running the Application](#running-the-application)
8. [API Endpoints](#api-endpoints)
9. [Troubleshooting](#troubleshooting)
10. [License](#license)

## Prerequisites

Before setting up the project, ensure you have the following installed:

- **Java JDK**: [Install Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or later)

## Setup

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/hpatel-27/bonsai-project.git
   cd bonsai-project


## Project Structure

```bash
/bonsai-project
├── /Bonsai                  # Spring Boot backend
│   ├── /src/main/java
│   │   └── /hpatel/Bonsai   # Java source files
│   │       ├── /config      # Security and configuration classes
│   │       ├── /controllers # REST controllers
│   │       ├── /models      # JPA entities
│   │       ├── /repositories# JPA repositories
│   ├── application.properties # Spring Boot configuration
│   └── pom.xml              # Maven configuration
```

