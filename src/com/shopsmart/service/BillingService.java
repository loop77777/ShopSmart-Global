package com.shopsmart.service;

import com.shopsmart.config.SystemEnvironmentConfig;
import com.shopsmart.model.Customer;
import com.shopsmart.model.Order;
import com.shopsmart.model.Product;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Responsible for generating customer tax invoices and billing summaries.
 * It uses StringBuilder for efficient output construction and String.format to
 * keep the receipt layout readable and consistent.
 */
public class BillingService {
    public String generateTaxInvoice(Customer customer, Order order, double finalTotal) {
        StringBuilder sb = new StringBuilder();
        StringBuffer auditBuffer = new StringBuffer("Secure audit trace initiated.");

        LocalDate today = LocalDate.now();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        NumberFormat currencyFmt = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println("[Audit Log]: " + auditBuffer.toString());

        double tax = finalTotal * SystemEnvironmentConfig.getInstance().getGlobalTaxRate();
        double grandTotal = finalTotal + tax;

        sb.append("=========================================\n");
        sb.append("        SHOPSMART GLOBAL TAX INVOICE     \n");
        sb.append("=========================================\n");
        sb.append("Date        : ").append(today.format(dtFormatter)).append("\n");
        sb.append("Customer    : ").append(customer.getName()).append("\n");
        sb.append("Order ID    : ").append(order.getOrderId()).append("\n");
        sb.append("-----------------------------------------\n");
        for (Product product : order.getProducts()) {
            sb.append(String.format(" - %-10s : %s\n", product.getName(), currencyFmt.format(product.getPrice())));
        }
        sb.append("-----------------------------------------\n");
        sb.append("Subtotal    : ").append(currencyFmt.format(finalTotal)).append("\n");
        sb.append("Tax (8%)    : ").append(currencyFmt.format(tax)).append("\n");
        sb.append("Grand Total : ").append(currencyFmt.format(grandTotal)).append("\n");
        sb.append("=========================================");

        return sb.toString();
    }
}