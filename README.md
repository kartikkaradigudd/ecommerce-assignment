# Ecommerce Store - Cart & Checkout API

## Overview
This project implements a simple e-commerce backend with APIs for adding items to the cart, checking out, and applying discount codes. It also includes admin APIs for generating discount codes and retrieving sales statistics.

## Features
- Add items to cart
- Remove from Cart
- Checkout with discount validation
- Auto-generate discount codes for every nth order if existing codes are expired ( I've set validTill field for checking this )
- Admin API for sales insights
- User authentication with role-based access control (Admin/User)
- 

## Tech Stack
- **Backend:** Spring Boot (Java 1.8)
- **Testing:** JUnit
- **Frontend:** Angular

## Project Structure
After cloning the repository, you will find two main directories:

- `client/` - Contains the Angular frontend.
- `server/` - Contains the Spring Boot backend.

## Setup Instructions

### Prerequisites
- Java 1.8+
- Spring Boot
- Postman (for API testing) (optional)
- Angular 6.2.9

### Clone the Repository
```sh
git clone https://github.com/kartikkaradigudd/ecommerce-assignment.git
cd ecommerce-assignment
git checkout develop
```

### Configuration
The application is configured to apply a discount on every **5th order**. This is set in the `application.properties` file:
```properties
# Configuration for nth order discount
nthOrder=5
```

### Run the Application
Navigate to the `server/` directory and start the backend:
```sh
cd server
./gradlew bootRun
```

Navigate to the `client/` directory and start the frontend:
```sh
cd client
npm install
ng serve
```

## API Documentation

### 1. Add Items to Cart
This API will add the product to User's Cart.

**Endpoint:** `POST /cart/addProductToCart`
- **Request Body:**
```json
{
    "username":"user1",
    "product":{
        "id":1,
        "name":"Laptop",
        "price":50000,
        "imageUrl":"assets/images/laptop.jpeg"
    }
}
```
- **Response:**
```json
{
    "status": "OK",
    "content": {
        "status": "OK",
        "message": "Successfully added product to the cart"
    }
}
```

## User Roles & Login Credentials

This application supports two roles: **Admin** and **User**.

### Users:
| Username | Password | Role  |
|----------|----------|------|
| user1    | user123  | User |
| user2    | user123  | User |
| user3    | user123  | User |
| admin    | admin123 | Admin |

- **Admin Role:** Can view sales statistics.
- **User Role:** Can add items to the cart and checkout but cannot access admin functionalities.

## Testing
Run tests using:
```sh
cd server
./gradlew test
```





