package com.shopsmart.model;

/**
 * Base class for all user roles in the system.
 * It centralizes common profile details and forces subclasses to provide
 * a role-specific dashboard representation.
 */
public abstract class User {
    private String userId;
    private String name;
    private String email;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public abstract void getDashboardDetails();

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
