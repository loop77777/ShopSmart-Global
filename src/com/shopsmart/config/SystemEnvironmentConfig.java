package com.shopsmart.config;

public class SystemEnvironmentConfig {
    private static SystemEnvironmentConfig instance;
    private double globalTaxRate;
    private String currencyCode;

    private SystemEnvironmentConfig() {
        this.globalTaxRate = 0.08; // 8% Tax
        this.currencyCode = "USD";
    }

    public static synchronized SystemEnvironmentConfig getInstance() {
        if (instance == null) {
            instance = new SystemEnvironmentConfig();
        }
        return instance;
    }

    public double getGlobalTaxRate() { return globalTaxRate; }
    public String getCurrencyCode() { return currencyCode; }
}