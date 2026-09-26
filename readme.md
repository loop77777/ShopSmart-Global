Problem Statement & Domain Context
1. Domain Context
   EduCore Online Academy requires an enterprise-grade Online Training Management System to handle digital course creation, trainer assignments, student registrations, multi-course enrollments, and automated certification logging. The software must be engineered in Core Java to demonstrate mastery over object-oriented design, design patterns, and low-level Java fundamentals.

2. Architectural Highlights & Requirements
   Coupling Demonstration:

Tightly Coupled: Direct concrete object creation inside business logic.

Loosely Coupled: Dependency injection via interfaces (e.g., NotificationSender interface implemented by EmailNotifier and SMSNotifier).

Relationships:

One-to-One: A Trainer maps to one primary TrainerProfile.

One-to-Many: A Course contains multiple Module entries.

Many-to-Many: Students can enroll in multiple Courses, and Courses contain multiple Students using Collection frameworks (Set / List).

Design Patterns: Singleton (for configuration logs), Factory (for course generation), and Strategy (for assessment grading strategies).

E-Commerce & Smart Supply Chain Management System (ShopSmart Global)
1. Domain Context & Background
   ShopSmart Global requires a high-performance, modular E-Commerce and Supply Chain Management Platform to handle multi-vendor product catalogs, customer profile onboarding, shopping cart management, dynamic order routing, and automated tax-inclusive invoicing.

The application must be architected using Core Java, strictly incorporating SOLID Design Principles, the Four Pillars of Object-Oriented Programming (OOP), tight vs. loose coupling, and core Design Patterns (Singleton, Factory, and Strategy) to ensure robust scalability and maintainability.

2. System Actors
   Customer: Browses the product catalog, manages a shopping cart, places multi-item orders, and tracks order fulfillment statuses.

Vendor / Merchant: Manages product inventories, updates stock levels, and fulfills assigned order sub-shipments.

Warehouse Manager: Oversees regional fulfillment hubs, shipping logistics, and inventory re-stocking queues.

Administrator: Oversees global application configurations, fee structures, user privilege tiers, and security audits.

3. Functional RequirementsFR1: User & Profile Management (Core OOP & Encapsulation)The system must implement an abstract base class (User) encapsulating core attributes (userId, name, email, accountStatus) with private fields and standard getter/setter encapsulation.Subclasses (Customer, Vendor, Administrator) must inherit from User and override polymorphic behavior methods (e.g., getDashboardDetails()).The system must enforce unique identifiers and provide $O(1)$ lookups and sorted sorting mechanisms using Java Collections (HashMap and TreeSet).

FR2: Product Catalog & Order Relationships (1-1, 1-N, M-N)
One-to-One Association: A Customer must map securely to a primary ShippingAddress.

One-to-Many Relationship: A Customer can place multiple Order records over time.

Many-to-Many Relationship: An Order can contain multiple Product items, and a Product can appear across multiple customer orders, managed via collection lists/sets.


FR3: Enterprise Design Patterns & Modularity
Singleton Pattern: Must be utilized for global platform configurations (e.g., SystemEnvironmentConfig) to manage currency rates and global tax caps.

Factory Pattern: Must be implemented via a PaymentProcessorFactory to dynamically instantiate payment gateways (e.g., Credit Card, UPI, PayPal) at runtime.

Strategy Pattern: Must be applied for dynamic operational behaviors, such as switching between different promotional discount strategies (SeasonalDiscountStrategy vs. LoyaltyDiscountStrategy).

FR4: Dynamic Billing, Invoicing & Text Processing
The system must calculate total checkout values (Base Price + Shipping Fees + Regional Taxes - Discounts).

It must leverage StringBuilder, StringBuffer, and String.format() to generate clean, thread-safe, and formatted electronic sales receipts and tax invoices.

+---------------------------------------------------------------+
|                   SystemEnvironmentConfig                     |
|                     (Singleton Pattern)                       |
|---------------------------------------------------------------|
| - static instance: SystemEnvironmentConfig                    |
| - globalTaxRate: double                                       |
|---------------------------------------------------------------|
| - SystemEnvironmentConfig()                                   |
| + static getInstance(): SystemEnvironmentConfig               |
| + getGlobalTaxRate(): double                                  |
+---------------------------------------------------------------+

+---------------------------------------------------------------+
|                      <<interface>>                            |
|                     PaymentProcessor                          |
|                     (Factory Pattern)                         |
|---------------------------------------------------------------|
| + processPayment(amount: double): boolean                     |
+---------------------------------------------------------------+
^                                       ^
| Implements                            | Implements
+-------------------------------+       +--------------------------------+
|     CreditCardProcessor       |       |          UpiProcessor          |
+-------------------------------+       +--------------------------------+
^
| Created by
+---------------------------------------------------------------+
|                    PaymentProcessorFactory                    |
|---------------------------------------------------------------|
| + static getProcessor(type: String): PaymentProcessor         |
+---------------------------------------------------------------+

+---------------------------------------------------------------+
|                      <<interface>>                            |
|                     DiscountStrategy                          |
|                     (Strategy Pattern)                        |
|---------------------------------------------------------------|
| + applyDiscount(amount: double): double                       |
+---------------------------------------------------------------+
^                                       ^
| Implements                            | Implements
+-------------------------------+       +--------------------------------+
|   SeasonalDiscountStrategy    |       |   LoyaltyDiscountStrategy      |
+-------------------------------+       +--------------------------------+

+---------------------------------------------------------------+
|                       <<abstract>> User                       |
|---------------------------------------------------------------|
| - userId: String, name: String, email: String                 |
|---------------------------------------------------------------|
| + {abstract} getDashboardDetails(): void                      |
+---------------------------------------------------------------+
^                                       ^
| Inheritance                           | Inheritance
+-------------------------------+       +--------------------------------+
|           Customer            |       |            Vendor              |
|-------------------------------|       |--------------------------------|
| - address: ShippingAddress (1-1)|     | - catalog: Set<Product> (1-N)  |
| - orders: List<Order> (1-N)   |       +--------------------------------+
+-------------------------------+                       
|                                               
| 1-to-1 Association                            
v                                               
+-------------------------------+                       
|        ShippingAddress        |                       
|-------------------------------|                       
| - street: String, city: String|                       
+-------------------------------+

+---------------------------------------------------------------+
|                             Order                             |
|---------------------------------------------------------------|
| - orderId: String                                             |
| - products: Set<Product> (Many-to-Many via Collections)       |
+---------------------------------------------------------------+
^
| Associated with
+---------------------------------------------------------------+
|                            Product                            |
|---------------------------------------------------------------|
| - productId: String, name: String, price: double              |
+---------------------------------------------------------------+

src/
└── com/
└── shopsmart/
├── config/
│   └── SystemEnvironmentConfig.java
├── payment/
│   ├── PaymentGateway.java
│   ├── CreditCardGateway.java
│   ├── UpiGateway.java
│   └── PaymentGatewayFactory.java
├── promotion/
│   ├── DiscountStrategy.java
│   ├── SeasonalDiscountStrategy.java
│   ├── LoyaltyDiscountStrategy.java
│   └── CheckoutContext.java
├── model/
│   ├── OrderStatus.java
│   ├── User.java
│   ├── ShippingAddress.java
│   ├── Product.java
│   ├── Order.java
│   └── Customer.java
├── repository/
│   └── OrderRepository.java
├── service/
│   ├── ECommerceService.java
│   └── BillingService.java
└── main/
└── ShopSmartIntegratedApp.java

---------------------------------------------------------------------------------------------------

Project Achievement Summary

This project was completed by converting the initial design into a working Java-based e-commerce platform that follows the required design patterns, object-oriented principles, and collection-based modeling.

We started by identifying the mismatch between the README requirements and the actual code structure. The main issue was that the project had the right domain idea but lacked the correct package structure, method names, and design-pattern implementation. We then rebuilt the project around the required architecture and made sure it works from the main application entry point.

Step-by-step completion process:

1. Configuration layer setup
   - Created and finalized the Singleton configuration for global system settings.
   - SystemEnvironmentConfig holds the shared global tax rate and currency configuration.
   - This ensures all modules read consistent platform settings rather than hardcoded values.

2. Payment gateway implementation
   - Created the PaymentGateway interface to define the transaction contract.
   - Implemented CreditCardGateway and UpiGateway as concrete payment providers.
   - Built the PaymentGatewayFactory class to return the correct gateway based on runtime input.
   - This demonstrates loose coupling and the Factory Design Pattern.

3. Promotion and discount strategy setup
   - Created the DiscountStrategy interface for interchangeable pricing logic.
   - Implemented SeasonalDiscountStrategy and LoyaltyDiscountStrategy.
   - Built CheckoutContext to decide which discount strategy is applied during checkout.
   - This demonstrates the Strategy Design Pattern clearly and cleanly.

4. Domain model creation and relationships
   - Created the User abstract base class with common profile details.
   - Implemented Customer as a specific user with shipping address and order history.
   - Added ShippingAddress as the one-to-one customer location model.
   - Added Product to represent catalog items.
   - Added Order to represent a purchase with multiple products.
   - Added OrderStatus as the order lifecycle enum.
   - These classes model the core e-commerce and supply-chain relationships: one-to-one, one-to-many, and many-to-many patterns.

5. Repository and storage layer
   - Created OrderRepository to store orders in a Map for quick lookup.
   - Added a TreeSet for sorted order ID tracking to fulfill the requirement for sorted retrieval and lookup support.
   - This shows practical usage of Java collections for scalability and fast order access.

6. Billing and tax invoice logic
   - Implemented BillingService to generate tax invoices for customers.
   - Used StringBuilder and String.format to create formatted receipts.
   - Used StringBuffer for audit-trace safety demonstration.
   - Calculated subtotal, tax, and grand total using the configured tax rate.

7. Java fundamentals and service-layer demonstration
   - Implemented ECommerceService to show pass-by-value semantics.
   - Demonstrated that primitive values do not change outside the method, while object references still mutate their referenced data.
   - This helps explain low-level Java behavior in a real business application.

8. Application flow and system integration
   - Built the main class ShopSmartIntegratedApp as the program entry point.
   - The app collects customer details, asks for products, applies the selected discount strategy, chooses a payment method, creates an invoice, and displays the final dashboard.
   - This ties the entire system together into one complete working demo.

---------------------------------------------------------------------------------------------------

Classes Used and Their Purpose

1. com.shopsmart.config.SystemEnvironmentConfig
   - Singleton configuration class
   - Controls global tax rate and currency settings

2. com.shopsmart.model.User
   - Abstract base class for all users
   - Encapsulates common user details such as userId, name, and email

3. com.shopsmart.model.Customer
   - Inherits from User
   - Stores shipping address and customer order history
   - Displays dashboard summary

4. com.shopsmart.model.ShippingAddress
   - Holds street and city information
   - Represents the one-to-one customer shipping association

5. com.shopsmart.model.Product
   - Represents an item in the product catalog
   - Stores product ID, name, and price

6. com.shopsmart.model.Order
   - Stores order ID and products in a Set
   - Models a purchase containing multiple products

7. com.shopsmart.model.OrderStatus
   - Enum representing order lifecycle states such as PENDING, SHIPPED, DELIVERED, CANCELLED

8. com.shopsmart.payment.PaymentGateway
   - Interface for payment methods

9. com.shopsmart.payment.CreditCardGateway
   - Simulates a credit card payment channel

10. com.shopsmart.payment.UpiGateway
   - Simulates a UPI payment gateway

11. com.shopsmart.payment.PaymentGatewayFactory
   - Creates the appropriate payment gateway based on runtime input

12. com.shopsmart.promotion.DiscountStrategy
   - Strategy interface for pricing rules

13. com.shopsmart.promotion.SeasonalDiscountStrategy
   - Applies a seasonal promotion discount

14. com.shopsmart.promotion.LoyaltyDiscountStrategy
   - Applies a loyalty-based VIP discount

15. com.shopsmart.promotion.CheckoutContext
   - Uses the selected discount strategy at checkout time

16. com.shopsmart.repository.OrderRepository
   - Stores and retrieves orders efficiently using Java collections

17. com.shopsmart.service.BillingService
   - Generates invoice and tax output for sales transactions

18. com.shopsmart.service.ECommerceService
   - Demonstrates pass-by-value behavior and service functions

19. com.shopsmart.main.ShopSmartIntegratedApp
   - Main application class that runs the complete e-commerce flow

---------------------------------------------------------------------------------------------------

Project Structure and Organization

The project is structured using a clean package-based architecture that separates responsibilities into logical modules:

- config: system-wide configuration and singleton settings
- model: all domain objects and data entities
- payment: payment interfaces and gateway implementations
- promotion: discount strategy pattern logic
- repository: data access and order storage
- service: billing and service-layer logic
- main: application startup and business flow control

This structure is easy to maintain because every layer has a specific purpose. Domain logic is separated from the business workflow and service operations. The code is modular, extendable, and aligned with the principles of Object-Oriented Programming and Design Patterns.

---------------------------------------------------------------------------------------------------

Final Outcome

The project now includes a working e-commerce checkout simulation with:
- customer onboarding
- product selection
- order creation
- discount strategy application
- payment processing
- tax invoice generation
- dashboard display
- application-level integration in one runnable Java program

This satisfies the project requirements in a practical way while keeping the design simple, readable, and aligned with learning objectives for Core Java, OOP, design patterns, and collections.

---------------------------------------------------------------------------------------------------

How to Run the Project

1. Open a terminal in the project root folder.
2. Compile all Java files:

   javac -d out $(find src -name "*.java")

   For Windows PowerShell, use:

   javac -d out $(Get-ChildItem -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)

3. Run the application:

   java -cp out com.shopsmart.main.ShopSmartIntegratedApp

4. Example input flow:

   Alice Johnson
   alice@example.com
   12 Market Street
   Bengaluru
   1
   2
   4
   2
   UPI

5. The program will then generate the order, apply the selected discount strategy, process the payment, and print the final invoice.

---------------------------------------------------------------------------------------------------

