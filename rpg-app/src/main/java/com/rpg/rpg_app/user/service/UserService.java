package com.rpg.rpg_app.user.service;

import com.rpg.rpg_app.user.entity.User;
import com.rpg.rpg_app.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserIService {

    private final UserRepository userRepository;

    //Função para validação de campos.
    private void validation(User user) {
        if (user == null ||
                !StringUtils.hasText(user.getUsername()) ||
                !StringUtils.hasText(user.getPassword()) ||
                !StringUtils.hasText(user.getEmail())) {
            throw new IllegalArgumentException("Os  campos obrigatórios não podem estar vazios.");
        }

    }

    //Função de salvar usuario.
    @Override
    public User save(User user) {
        validation(user);
        return userRepository.save(user);
    }

    //Função de dar update num usuario.
    @Override
    public User update(User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException("O usuario não foi encontrado para atualizar.");
        }

        validation(user);
        return userRepository.save(user);

    }

    //Funcao de apagar um usuario.
    @Override
    public void delete(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("O usuário não foi econtrado para deletar.");
        }

        userRepository.delete(user);

    }

    //Função de encontrar usuario por ID.
    public User findById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("O usuário não foi encontrado.");
        }

        return userRepository.findById(id).orElse(null);

    }

    //Função para retornar todos os usuários.
    @Override
    public List<User> findAll() {

        return userRepository.findAll();

    }

    //Função para encontrar usuário por username.
    @Override
    public List<User> findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

    //Função para encontrar usuario por email.
    @Override
    public List<User> findByEmail(String email) {

        return userRepository.findByEmail(email);

    }

}
