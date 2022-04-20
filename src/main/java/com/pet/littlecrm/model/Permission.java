package com.pet.littlecrm.model;

public enum Permission {
    PEOPLE_READ("people:read"),
    PEOPLE_WRITE("people:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
