package com.rpg.rpg_app.rpgcharacter.service;

import java.util.List;
import com.rpg.rpg_app.rpgcharacter.entity.RpgCharacter;

public interface RpgCharacterIService {

    //Funçao teste para testar a 'api'.
    public abstract String printNameCharacter(String name);

    //Função de salvar personagem.
    public abstract RpgCharacter save (RpgCharacter rpgCharacter);

    //Função de dar update num personagem.
    public abstract RpgCharacter update(RpgCharacter rpgCharacter);

    //Funcao de apagar um personagem.
    public abstract void delete(RpgCharacter rpgCharacter);

    //Função de encontrar personagem por ID.
    public RpgCharacter findById(Long id);

    //Função para retornar todos os personagens.
    public abstract List<RpgCharacter> findAll();

    //Função para encontrar personagem por nome.
    public abstract List<RpgCharacter> findByName(String name);

    //Função para encontrar personagem por raça.
    public abstract List<RpgCharacter> findByRaca(String raca);

}
