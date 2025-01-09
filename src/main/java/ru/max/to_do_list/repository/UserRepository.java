package ru.max.to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.max.to_do_list.models.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long id);
    Optional<User> findByEmail(String email);
}
