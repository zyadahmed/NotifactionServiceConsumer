# NotificationService

## Overview

`NotificationService` is a Spring Boot application that processes real-time transaction notifications. It receives transaction data from RabbitMQ, manages WebSocket connections, and sends notifications to users. The service includes authentication via headers and manages WebSocket sessions in memory.

## Features

- **Transaction Processing**: Receives transaction data from RabbitMQ.
- **WebSocket Notifications**: Connects with users via WebSocket and sends real-time updates.
- **Authentication**: Intercepts requests with an authentication header.
- **Session Management**: Maintains WebSocket sessions in memory.

