package ru.max.to_do_list.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.max.to_do_list.models.auth.JwtAuthentication;
import ru.max.to_do_list.models.auth.UserCredentials;
import ru.max.to_do_list.models.user.User;
import ru.max.to_do_list.models.response.Response;
import ru.max.to_do_list.service.UserService;

import javax.naming.AuthenticationException;


@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class AuthController {
    private final UserService service;

    @PostMapping("/registration")
    public Response createUser(@RequestBody User user) { return service.addUser(user); }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthentication> singIn(@RequestBody UserCredentials userCredentials) {
        try {
            JwtAuthentication jwtAuthentication = service.singIn(userCredentials);
            return ResponseEntity.ok(jwtAuthentication);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
