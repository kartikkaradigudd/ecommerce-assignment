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

### 2. Get cart details by username

This API will fetch the products which are added to user's cart.

**Endpoint:** `GET /cart/getCartDetailsByUsername/{username}`
- **Response:**
```json
{
    "status": "OK",
    "content": {
        "status": "OK",
        "message": "Fetched cart details successfully",
        "cart": {
            "username": "user1",
            "products": [
                {
                    "id": 1,
                    "name": "Laptop",
                    "price": 50000,
                    "imageUrl": "assets/images/laptop.jpeg"
                },
                {
                    "id": 2,
                    "name": "Phone",
                    "price": 20000,
                    "imageUrl": "assets/images/phone.jpeg"
                },
                {
                    "id": 1,
                    "name": "Laptop",
                    "price": 50000,
                    "imageUrl": "assets/images/laptop.jpeg"
                }
            ]
        }
    }
}
```

### 3. Remove Items to Cart

This API will remove the product from the Cart.

**Endpoint:** `POST /cart/removeProductFromCart`
- **Request Body:**
```json
{
    "username":"user1",
    "productId":1
}
```
- **Response:**
```json
{
    "status": "OK",
    "content": {
        "status": "OK",
        "message": "Product removed from cart"
    }
}
```

### 4. Order Validation / Checkout Validation API

This API will check if the order being placed is nthOrder. If it is nthOrder it will check for already existing Coupon Codes and assigns any valid coupon code. If all codes are expired(I'm checking date here to check for expiry) then this API will generate and gives back the coupon and then newly generated coupon gets added to Coupon Code List. Here use "user2" as path variable for getting couponCode and user "user1" for not getting couponCode because because it's not nth Order. 

**Endpoint:** `GET /checkout/validateOrder/{username}`

- **Response:**
```json
{
    "status": "OK",
    "content": {
        "status": "OK",
        "message": "Order Validated Successfully",
        "couponCode": "OFF10-SAVE123",
        "validForCoupon": true
    }
}
```

### 5. Place Order API

This API will place the Order. First this API will store the Order to order list, clears the products which are present in user's cart, increases the order count of user by one and adds the discount amount if any to the corresponding coupon code.

**Endpoint:** `POST /checkout/placeOrder`
- **Request Body:**
```json
{
    "username":"user1",
    "productIds":[1,2],
    "totalPaidAmount":65000,
    "totalDiscount":6500,
    "couponCode":"SAVE10-PLM45Z"
}
```
- **Response:**
```json
{
    "status": "OK",
    "content": {
        "status": "OK",
        "message": "Order Placed Successfully"
    }
}
```

### 6. Get all Products API

This API will fetch all the products.

**Endpoint:** `GET /products/getAllProducts`
- **Response:**
```json
{
    "status": "OK",
    "content": {
        "status": "OK",
        "message": "Fetched all Products",
        "products": [
            {
                "id": 1,
                "name": "Laptop",
                "price": 50000,
                "imageUrl": "assets/images/laptop.jpeg"
            },
            {
                "id": 2,
                "name": "Phone",
                "price": 20000,
                "imageUrl": "assets/images/phone.jpeg"
            },
            {
                "id": 3,
                "name": "T-Shirt",
                "price": 600,
                "imageUrl": "assets/images/t-shirt.jpeg"
            },
            {
                "id": 4,
                "name": "Jeans",
                "price": 1200,
                "imageUrl": "assets/images/jeans.jpeg"
            },
            {
                "id": 5,
                "name": "Microwave",
                "price": 13500,
                "imageUrl": "assets/images/microwave.jpeg"
            },
            {
                "id": 6,
                "name": "Vacuum Cleaner",
                "price": 4500,
                "imageUrl": "assets/images/vacuum-cleaner.jpeg"
            }
        ]
    }
}
```


### 7. Get Sales Stats

This API lists count of items purchased, total purchase amount, list of discount codes and total discount amount.

**Endpoint:** `GET /admin/getSalesStats`
- **Response:**
```json
{
    "status": "OK",
    "content": {
        "status": "OK",
        "message": "Sales stats fetched successfully",
        "noOfItemsPurchased": 6,
        "totalPurchaseAmount": 90000,
        "couponCodes": [
            {
                "code": "DISCOUNT10-JKH67D",
                "validTill": "2025-02-20T18:30:00.000+00:00",
                "totalDiscountedAmount": 6000
            },
            {
                "code": "OFF10-SAVE123",
                "validTill": "2025-05-03T18:30:00.000+00:00",
                "totalDiscountedAmount": 600
            },
            {
                "code": "TENOFF-YUI89T",
                "validTill": "2025-06-24T18:30:00.000+00:00",
                "totalDiscountedAmount": 7500
            },
            {
                "code": "GET10-MNB23Q",
                "validTill": "2025-05-05T18:30:00.000+00:00",
                "totalDiscountedAmount": 4200
            },
            {
                "code": "SAVE10-PLM45Z",
                "validTill": "2025-06-30T18:30:00.000+00:00",
                "totalDiscountedAmount": 8300
            }
        ]
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





