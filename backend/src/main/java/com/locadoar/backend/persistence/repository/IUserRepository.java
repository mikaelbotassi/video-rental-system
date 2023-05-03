package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {

    User findUserByLoginAndPassword(String login, String password);
}
