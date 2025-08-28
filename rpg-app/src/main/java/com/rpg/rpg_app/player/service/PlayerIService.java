package com.rpg.rpg_app.player.service;


import com.rpg.rpg_app.player.entity.Player;

import java.util.List;

public interface PlayerIService {

    //Função de salvar um user do tipo Player.
    public abstract Player save (Player player);

    //Função para modificar um user do tipo Player.
    public abstract Player update(Player player);

    //Funcao de apagar um user do tipo Player.
    public abstract void delete(Player player);

    //Função de encontrar um user do tipo Player por ID.
    public Player findById(Long id);

    //Função para retornar todos os players.
    public abstract List<Player> findAll();

    //Função para retornar pelo nome.
    public abstract List<Player> findByName(String name);

    //Função para retornar pelo email.
    public abstract List<Player> findByEmail(String email);

}
