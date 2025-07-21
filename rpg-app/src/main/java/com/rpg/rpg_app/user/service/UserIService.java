package com.rpg.rpg_app.user.service;

import com.rpg.rpg_app.user.entity.User;
import java.util.List;

public interface UserIService {

    //Funçao teste usada para testar a 'api'.
    public abstract String printUserName(String username);

    //Função de salvar usuario.
    public abstract void save(User user);

    //Função de dar update num usuario.
    public abstract void update(User user);

    //Funcao de apagar um usuario.
    public abstract void delete(User user);

    //Função de encontrar usuario por ID.
    public abstract User findById(Long id);

    //Função para retornar todos os usuários.
    public abstract List<User> findAll();

    //Função para encontrar usuário por username.
    public abstract List<User> findByUsername(String username);

    //Função para encontrar usuario por email.
    public abstract List<User> findByEmail(String email);

}
