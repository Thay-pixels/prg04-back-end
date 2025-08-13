package com.rpg.rpg_app.character.service;

import java.util.List;
import com.rpg.rpg_app.character.entity.Character;

public interface CharacterIService {

    //Funçao teste para testar a 'api'.
    public abstract String printNameCharacter(String name);

    //Função de salvar personagem.
    public abstract Character save (Character character);

    //Função de dar update num personagem.
    public abstract Character update(Character character);

    //Funcao de apagar um personagem.
    public abstract void delete(Character character);

    //Função de encontrar personagem por ID.
    public Character findById(Long id);

    //Função para retornar todos os personagens.
    public abstract List<Character> findAll();

    //Função para encontrar personagem por nome.
    public abstract List<Character> findByName(String name);

    //Função para encontrar personagem por raça.
    public abstract List<Character> findByRaca(String raca);

}
