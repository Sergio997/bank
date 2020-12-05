package com.bank.model.enums;

public enum Permission {

    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_PREMIUM_READ("developers:premium_read"),
    DEVELOPERS_WRITE("developers:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
