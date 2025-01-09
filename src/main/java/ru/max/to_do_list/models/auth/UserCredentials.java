package ru.max.to_do_list.models.auth;

import lombok.Data;

@Data
public class UserCredentials {
    private String email;
    private String password;
}
