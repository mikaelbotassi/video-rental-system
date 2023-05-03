package com.locadoar.backend.controller;

import com.locadoar.backend.domain.User;
import com.locadoar.backend.persistence.repository.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody User user) {
        User dbuser = userRepository.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if(dbuser != null) return ResponseEntity.ok("Login efetuado com sucesso!");
        else return ResponseEntity.badRequest().body("Usuario não encontrado");
    }
}
