package ru.max.to_do_list.models.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }
}
