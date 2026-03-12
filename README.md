# 🛒 Spring Boot Inventory Management Application

A customized full-stack inventory management system built with **Spring Boot**, **Thymeleaf**, and an **HTML/CSS front-end**. The application allows a retail store to manage parts and products, enforce inventory rules, and process purchases — all through a web-based interface.

> Built as part of **WGU D287 — Java Frameworks**

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| View Layer | Thymeleaf + HTML/CSS |
| Persistence | Spring Data JPA (H2 / persistent storage) |
| Build Tool | Maven |
| IDE | IntelliJ IDEA (Ultimate) |
| Testing | JUnit (PartTest class) |

---

## Scenario

This application was customized for a specific retail store that sells products made up of component parts. The store's inventory, UI branding, and business rules were all implemented as part of this project.

---

## Features

- **Custom storefront UI** — Shop name, product names, and part names branded to the chosen customer
- **About page** — Company description page with navigation to and from the main screen
- **Sample inventory** — Five parts and five products pre-loaded on first run (does not overwrite existing data)
- **Buy Now button** — Decrements product inventory by one per purchase; displays success or failure message
- **Min/Max inventory tracking** — Each part enforces a minimum and maximum stock level
- **Input validation** — Error messages shown when inventory falls below minimum or exceeds maximum, on both parts and products
- **Unit tests** — JUnit tests covering min/max field logic in the `PartTest` class
- **Clean code** — Unused validator classes removed from the codebase

---

## Code Change Reference (Parts C–J)

> This section documents where each requirement was implemented, per the project spec.

### Part C — Customize HTML User Interface
- **File:** `src/main/resources/templates/mainscreen.html`
- **Change:** Updated shop name in the page header; replaced generic product/part label text with store-specific names

### Part D — About Page
- **Files:**
  - `src/main/resources/templates/about.html` *(new file)*
  - `src/main/java/.../controllers/AboutController.java` *(new file)*
  - `src/main/resources/templates/mainscreen.html`
- **Change:** Created About page with company description; added navigation link from main screen to About page and back

### Part E — Sample Inventory
- **File:** `src/main/java/.../Bootstrap.java` (or `BootStrapData.java`)
- **Change:** Added five sample parts and five sample products; wrapped in a conditional check so data is only inserted when both lists are empty

### Part F — Buy Now Button
- **Files:**
  - `src/main/resources/templates/mainscreen.html`
  - `src/main/java/.../controllers/BuyProductController.java` *(new file)*
  - `src/main/resources/templates/buyProductError.html` *(new file)*
  - `src/main/resources/templates/buyProductSuccess.html` *(new file)*
- **Change:** Added "Buy Now" button next to Update/Delete on the product list; controller decrements product inventory by one and routes to success or error page

### Part G — Min/Max Inventory Fields
- **Files:**
  - `src/main/java/.../entities/Part.java`
  - `src/main/resources/templates/InhousePartForm.html`
  - `src/main/resources/templates/OutsourcedPartForm.html`
  - `src/main/java/.../Bootstrap.java`
  - `src/main/resources/application.properties`
- **Change:** Added `minInv` and `maxInv` fields to the `Part` entity; added form inputs for both fields in both part forms; seeded min/max values in sample data; renamed persistent storage file

### Part H — Validation
- **Files:**
  - `src/main/java/.../validators/ValidEnoughPartsValidator.java`
  - `src/main/java/.../validators/ValidInventoryLevelValidator.java` *(or equivalent)*
  - `src/main/resources/templates/InhousePartForm.html`
  - `src/main/resources/templates/OutsourcedPartForm.html`
  - `src/main/resources/templates/productForm.html`
- **Change:** Added validation logic to enforce inventory between min and max; added Thymeleaf error message display in all relevant forms

### Part I — Unit Tests
- **File:** `src/test/java/.../PartTest.java`
- **Change:** Added `testGetMinInv()` and `testGetMaxInv()` (and setters) unit tests verifying min/max field behavior on the `Part` entity

### Part J — Clean Code
- **File:** `src/main/java/.../validators/` directory
- **Change:** Deleted unused validator class files that were not referenced anywhere in the application

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- IntelliJ IDEA (Ultimate Edition recommended)

### Run the Application

```bash
git clone https://github.com/YOUR_USERNAME/spring-inventory-management.git
cd spring-inventory-management
mvn spring-boot:run
```

Navigate to `http://localhost:8080` to view the application.


---

## License

This project was completed as an academic assessment for WGU. Shared for portfolio purposes only.
