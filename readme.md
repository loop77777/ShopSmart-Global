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

