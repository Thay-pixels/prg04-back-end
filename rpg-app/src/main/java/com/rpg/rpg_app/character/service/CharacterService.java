package com.rpg.rpg_app.character.service;

import com.rpg.rpg_app.character.entity.Character;
import com.rpg.rpg_app.character.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    //Funçao teste para testar a 'api'.
    public String printNameCharacter(String name) {
        return "Character " + name;
    }

    //Função de salvar personagem.
    public void save(Character character) {

        characterRepository.save(character);

    }

    //Função de dar update num personagem.
    public void update(Character character) {

        characterRepository.save(character);

    }

    //Funcao de apagar um personagem.
    public void delete(Character character) {

        characterRepository.delete(character);

    }

    //Função de encontrar personagem por ID.
    public Character findById(Long id) {

        return characterRepository.findById(id).orElse(null);

    }

    //Função para retornar todos os personagens.
    public List<Character> findAll() {

        return characterRepository.findAll();

    }

    //Função para encontrar personagem por nome.
    public List<Character> findByName(String name) {

        return characterRepository.findByName(name);
    }

    //Função para encontrar personagem por raça.
    public List<Character> findByRaca(String raca) {

        return characterRepository.findByRaca(raca);
    }

}