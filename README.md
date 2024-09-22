# Bonsai Project

## Introduction

Bonsai is a full-stack web application designed to manage and track workouts. It consists of a React frontend and a Spring Boot backend, both containerized and orchestrated using Docker Compose. The application allows users to view, add, and track workout routines while interacting with a MySQL database.

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

- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)
- **Node.js**: [Install Node.js](https://nodejs.org/) (version 14.x or later)
- **Java JDK**: [Install Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or later)

## Setup

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/bonsai-project.git
   cd bonsai-project


## Project Structure

```bash
/bonsai-project
│
├── /bonsai-frontend         # React frontend
│   ├── /public              # Public assets
│   ├── /src                 # React source files
│   │   ├── /components      # React components
│   │   ├── /services        # API services
│   │   ├── /hooks           # Custom hooks
│   │   ├── App.jsx          # Main App component
│   │   └── main.jsx         # Entry point
│   ├── package.json         # Frontend dependencies
│   └── vite.config.js       # Vite configuration
│
├── /Bonsai                  # Spring Boot backend
│   ├── /src/main/java
│   │   └── /hpatel/Bonsai   # Java source files
│   │       ├── /config      # Security and configuration classes
│   │       ├── /controllers # REST controllers
│   │       ├── /models      # JPA entities
│   │       ├── /repositories# JPA repositories
│   ├── application.properties # Spring Boot configuration
│   └── pom.xml              # Maven configuration
│
├── /nginx                   # Nginx configuration
│   └── nginx.conf           # Nginx configuration file
│
└── docker-compose.yml       # Docker Compose configuration
```

