package com.rpg.rpg_app.user.service;

import ch.qos.logback.core.util.StringUtil;
import com.rpg.rpg_app.user.entity.User;
import com.rpg.rpg_app.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //Função teste para testar se a 'api' funciona.
    public String printUserName(String username) {
        return "Bem vindo usuário :" + username;
    }

    //Função de salvar usuario.
    public void save(User user) {

        userRepository.save(user);

    }

    //Função de dar update num usuario.
    public void update(User user) {

        userRepository.save(user);

    }

    //Funcao de apagar um usuario.
    public void delete(User user) {

        userRepository.delete(user);

    }

    //Função de encontrar usuario por ID.
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);

    }

    //Função para retornar todos os usuários.
    public List<User> findAll() {

        return userRepository.findAll();

    }

    //Função para encontrar usuário por username.
    public List<User> findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

    //Função para encontrar usuario por email.
    public List<User> findByEmail(String email) {

        return userRepository.findByEmail(email);

    }

}
