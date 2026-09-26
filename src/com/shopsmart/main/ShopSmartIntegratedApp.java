package com.shopsmart.main;

import com.shopsmart.config.SystemEnvironmentConfig;
import com.shopsmart.model.Customer;
import com.shopsmart.model.Order;
import com.shopsmart.model.Product;
import com.shopsmart.model.ShippingAddress;
import com.shopsmart.payment.PaymentGateway;
import com.shopsmart.payment.PaymentGatewayFactory;
import com.shopsmart.promotion.CheckoutContext;
import com.shopsmart.promotion.LoyaltyDiscountStrategy;
import com.shopsmart.promotion.SeasonalDiscountStrategy;
import com.shopsmart.repository.OrderRepository;
import com.shopsmart.service.BillingService;
import com.shopsmart.service.ECommerceService;

import java.util.Scanner;

/**
 * Entry point for the ShopSmart Global platform demonstration.
 * This class orchestrates onboarding, product selection, strategy-based pricing,
 * payment processing, invoice generation, and reporting.
 */
public class ShopSmartIntegratedApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderRepository repository = new OrderRepository();
        BillingService billingService = new BillingService();
        ECommerceService eCommerceService = new ECommerceService();

        System.out.println("=================================================");
        System.out.println("   WELCOME TO SHOPSMART GLOBAL ENTERPRISE SYSTEM ");
        System.out.println("=================================================\n");

        // 1. Customer onboarding using a one-to-one shipping address.
        System.out.print("Enter Customer Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Customer Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Street Address: ");
        String street = scanner.nextLine();

        System.out.print("Enter City: ");
        String city = scanner.nextLine();

        ShippingAddress address = new ShippingAddress(street, city);
        Customer customer = new Customer("CUST-" + (System.currentTimeMillis() % 10000), name, email, address);

        System.out.println("\n[Success] Customer profile created successfully!\n");

        // 2. Build an order with a many-to-many product relationship model.
        Order order = new Order("ORD-" + (202600 + (int) (Math.random() * 1000)));

        Product p1 = new Product("PROD-101", "Enterprise Blade Server", 1200.00);
        Product p2 = new Product("PROD-102", "Managed Switch 24-Port", 450.00);
        Product p3 = new Product("PROD-103", "Cloud Storage Module 2TB", 299.99);

        boolean addingProducts = true;
        double subtotal = 0.0;

        while (addingProducts) {
            System.out.println("--- Product Catalog ---");
            System.out.println("1. " + p1.getName() + " - $" + p1.getPrice());
            System.out.println("2. " + p2.getName() + " - $" + p2.getPrice());
            System.out.println("3. " + p3.getName() + " - $" + p3.getPrice());
            System.out.println("4. Finish and Proceed to Checkout");
            System.out.print("Select an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    order.addProduct(p1);
                    subtotal += p1.getPrice();
                    System.out.println("-> Added " + p1.getName() + " to order.");
                    break;
                case 2:
                    order.addProduct(p2);
                    subtotal += p2.getPrice();
                    System.out.println("-> Added " + p2.getName() + " to order.");
                    break;
                case 3:
                    order.addProduct(p3);
                    subtotal += p3.getPrice();
                    System.out.println("-> Added " + p3.getName() + " to order.");
                    break;
                case 4:
                    addingProducts = false;
                    break;
                default:
                    System.out.println("[Error] Invalid selection. Try again.");
            }
            System.out.println();
        }

        if (order.getProducts().isEmpty()) {
            System.out.println("No products selected. Exiting system.");
            scanner.close();
            return;
        }

        // The customer has a one-to-many relationship with orders.
        customer.addOrder(order);
        repository.save(order);

        // 3. Strategy pattern for discount selection.
        System.out.println("--- Choose Discount Strategy ---");
        System.out.println("1. Seasonal Discount (10% Off)");
        System.out.println("2. Loyalty VIP Discount (20% Off)");
        System.out.println("3. No Discount");
        System.out.print("Select strategy (1-3): ");
        int discountChoice = scanner.nextInt();
        scanner.nextLine();

        CheckoutContext checkoutContext = new CheckoutContext();
        if (discountChoice == 1) {
            checkoutContext.setStrategy(new SeasonalDiscountStrategy());
        } else if (discountChoice == 2) {
            checkoutContext.setStrategy(new LoyaltyDiscountStrategy());
        }

        double discountedTotal = checkoutContext.applyStrategy(subtotal);

        // 4. Factory pattern for selecting a payment gateway at runtime.
        System.out.println("\n--- Choose Payment Method ---");
        System.out.println("1. Credit Card (CARD)");
        System.out.println("2. Instant UPI (UPI)");
        System.out.print("Enter choice (CARD / UPI): ");
        String paymentType = scanner.nextLine().trim();

        try {
            PaymentGateway gateway = PaymentGatewayFactory.getGateway(paymentType);
            gateway.executeTransaction(discountedTotal);
        } catch (Exception e) {
            System.out.println("[Warning] Invalid gateway choice. Defaulting to UPI simulation.");
            PaymentGateway gateway = PaymentGatewayFactory.getGateway("UPI");
            gateway.executeTransaction(discountedTotal);
        }

        // 5. Billing service generates the invoice using StringBuilder/String.format.
        System.out.println("\nGenerating Official Invoice...");
        String corporateInvoice = billingService.generateTaxInvoice(customer, order, discountedTotal);
        System.out.println("\n" + corporateInvoice);

        // 6. Demonstrate pass-by-value semantics as requested in the project requirements.
        int primitiveCount = 10;
        StringBuilder builder = new StringBuilder("Original value");
        eCommerceService.testPassByValueSemantics(primitiveCount, builder);
        System.out.println("\nPass-by-value demo: primitiveCount remains " + primitiveCount + ", builder changed to -> " + builder);

        // 7. Display dashboard summary and global configuration info.
        System.out.println("\n--- Final System State ---");
        customer.displayDashboard();
        System.out.println("Tax rate configured: " + SystemEnvironmentConfig.getInstance().getGlobalTaxRate());

        scanner.close();
    }
}