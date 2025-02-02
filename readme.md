# Feature Toggle Demo Project

A Spring Boot application demonstrating feature toggle implementation using Redis for distributed configuration management.

## Overview

This project implements a feature toggle system that allows dynamic behavior changes in a production environment without code deployment. It uses Redis as a centralized storage for feature flags and implements pub/sub for real-time updates across multiple application instances.

## Features

- Runtime feature flag management
- Redis-based distributed configuration
- Annotation-based toggle control (`@Toggle`)
- Fallback method support
- Real-time feature updates using Redis pub/sub

## Technical Stack

- Java 23
- Spring Boot 3.4.1
- Spring Data Redis
- Spring AOP
- Lombok
- Redis

## Project Structure

### Core Components

1. **Toggle Annotation**
```java
@Toggle(featureId = "feature:id", enabled = true, fallbackMethod = "fallbackMethod")