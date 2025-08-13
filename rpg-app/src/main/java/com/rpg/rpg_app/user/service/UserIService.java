package com.rpg.rpg_app.user.service;

import com.rpg.rpg_app.user.entity.User;
import java.util.List;

public interface UserIService {

    //Função de salvar usuario.
    public abstract User save(User user);

    //Função de dar update num usuario.
    public abstract User update(User user);

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
