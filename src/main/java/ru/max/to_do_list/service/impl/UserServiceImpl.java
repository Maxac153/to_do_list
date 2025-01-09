package ru.max.to_do_list.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.max.to_do_list.mapper.UserMapper;
import ru.max.to_do_list.models.auth.JwtAuthentication;
import ru.max.to_do_list.models.auth.RefreshToken;
import ru.max.to_do_list.models.auth.UserCredentials;
import ru.max.to_do_list.models.response.Response;
import ru.max.to_do_list.models.user.User;
import ru.max.to_do_list.models.user.UserDto;
import ru.max.to_do_list.repository.UserRepository;
import ru.max.to_do_list.security.jwt.JwtService;
import ru.max.to_do_list.service.UserService;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthentication singIn(UserCredentials userCredentials) throws AuthenticationException {
        User user = findByCredentials(userCredentials);
        return jwtService.generateAuthToken(user.getEmail());
    }

    @Override
    public JwtAuthentication refreshToken(RefreshToken refreshToken) throws Exception {
        String token = refreshToken.getRefreshToken();
        if (refreshToken != null && jwtService.validateJwtToken(token)) {
            User user = findByEmail(jwtService.getEmailFromToken(token));
            return jwtService.refreshBaseToken(user.getEmail(), token);
        }
        throw new  AuthenticationException("Invalid refresh token");
    }

    @Override
    public Response addUser(User user) {
        if (user == null) {
            return Response.builder()
                    .message("User data is missing!")
                    .status("ERROR")
                    .build();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return Response.builder()
                .message("User added successfully.")
                .status("SUCCESS")
                .build();
    }

    @Override
    @Transactional
    public UserDto getUserById(Long id) throws ChangeSetPersister.NotFoundException {
        return userMapper.toDto(userRepository.findByUserId(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    private User findByCredentials(UserCredentials userCredentials) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByEmail(userCredentials.getEmail());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (passwordEncoder.matches(userCredentials.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new AuthenticationException("Email or password is not correct");
    }

    private User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow(()->
                new Exception(String.format("User with email %s not found", email)));
    }
}
