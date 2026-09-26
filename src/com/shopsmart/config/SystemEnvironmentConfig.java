package com.shopsmart.config;

/**
 * Singleton configuration object used across the ShopSmart platform.
 * It centralizes global tax settings, currency preferences, and any
 * environment-level values required by billing and checkout operations.
 */
public class SystemEnvironmentConfig {
    private static SystemEnvironmentConfig instance;

    private double globalTaxRate;
    private String currencyCode;

    private SystemEnvironmentConfig() {
        this.globalTaxRate = 0.08; // 8% tax rate for online retail transactions
        this.currencyCode = "USD";
    }

    public static synchronized SystemEnvironmentConfig getInstance() {
        if (instance == null) {
            instance = new SystemEnvironmentConfig();
        }
        return instance;
    }

    public double getGlobalTaxRate() {
        return globalTaxRate;
    }

    public void setGlobalTaxRate(double globalTaxRate) {
        this.globalTaxRate = globalTaxRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}