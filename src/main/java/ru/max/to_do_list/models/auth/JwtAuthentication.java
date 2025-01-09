package ru.max.to_do_list.models.auth;

import lombok.Data;

@Data
public class JwtAuthentication {
    private String token;
    private String refreshToken;
}
