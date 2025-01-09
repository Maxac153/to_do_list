package ru.max.to_do_list.models.user;

import lombok.Data;

@Data
public class UserDto {
    Long userId;
    String firstName;
    String lastName;
    String email;
    String password;
}
