# Personal Budget Tracker - Docker Setup

This directory contains the Docker configuration for running the Personal Budget Tracker application with separate containers for frontend, backend, and database.

## Architecture

- **Frontend**: React application served by Nginx (port 3000)
- **Backend**: Spring Boot REST API (port 8080)
- **Database**: MySQL 8.0 (port 3306)

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (included with Docker Desktop)

## Quick Start

1. Navigate to the SAGENT directory:
   ```bash
   cd "C:\Users\Admin\Documents\Kevin\Projects\SAGENT"
   ```

2. Build and start all containers:
   ```bash
   docker compose up --build
   ```

3. Access the application:
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - Database: localhost:3306

## Docker Compose Commands

- **Start all services**: `docker compose up -d`
- **Stop all services**: `docker compose down`
- **View logs**: `docker compose logs -f`
- **View specific service logs**: `docker compose logs -f [frontend|backend|mysql]`
- **Rebuild a service**: `docker compose up --build [service-name]`

## Database Configuration

The MySQL container is configured with:
- Database: `budget_tracker`
- User: `budget_user`
- Password: `budget_password`
- Root password: `rootpassword`

## Development

### Frontend Development
- React app is built using multi-stage Docker build
- Nginx serves the production build
- API requests are proxied to the backend

### Backend Development
- Spring Boot app is built using Maven in a multi-stage Docker build
- Environment variables are used for database configuration
- The app connects to the MySQL container

### Container Networking
All containers communicate through a Docker bridge network named `budget-network`.

## Troubleshooting

1. **Port conflicts**: Ensure ports 3000, 8080, and 3306 are not in use
2. **Build failures**: Check Docker logs for specific error messages
3. **Database connection**: Verify the MySQL container is running before the backend

## Persistent Data

MySQL data is persisted in a Docker volume named `sagent_mysql_data`.
