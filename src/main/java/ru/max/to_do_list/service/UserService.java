package ru.max.to_do_list.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.max.to_do_list.models.auth.JwtAuthentication;
import ru.max.to_do_list.models.auth.RefreshToken;
import ru.max.to_do_list.models.auth.UserCredentials;
import ru.max.to_do_list.models.response.Response;
import ru.max.to_do_list.models.user.User;
import ru.max.to_do_list.models.user.UserDto;

import javax.naming.AuthenticationException;

public interface UserService {
    JwtAuthentication singIn(UserCredentials userCredentials) throws AuthenticationException;
    JwtAuthentication refreshToken(RefreshToken refreshToken) throws Exception;
    Response addUser(User user);
    UserDto getUserById(Long id) throws ChangeSetPersister.NotFoundException;
}
