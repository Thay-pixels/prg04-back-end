package com.rpg.rpg_app.character.service;

import com.rpg.rpg_app.character.entity.Character;
import com.rpg.rpg_app.character.repository.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService implements CharacterIService {

    private final CharacterRepository characterRepository;

    //Funçao teste para testar a 'api'.
    public String printNameCharacter(String name) {

        return "Character " + name;

    }

    //Função para validação de campos.
    private void validation(Character character) {
        if (character == null ||
                !StringUtils.hasText(character.getName()) ||
                !StringUtils.hasText(character.getRaca())) {
            throw new IllegalArgumentException("Os  campos obrigatórios não podem estar vazios.");
        }

    }

    //Função de salvar usuario.
    @Override
    public void save(Character character) {
        validation(character);
        characterRepository.save(character);

    }

    //Função de dar update num usuario.
    @Override
    public void update(Character character) {
        if (character.getId() == null || !characterRepository.existsById(character.getId())) {
            throw new EntityNotFoundException("O usuario não foi encontrado para atualizar.");
        }

        validation(character);
        characterRepository.save(character);

    }

    //Funcao de apagar um usuario.
    @Override
    public void delete(Character character) {
        if (character == null || character.getId() == null) {
            throw new IllegalArgumentException("O usuário não foi econtrado para deletar.");
        }

        characterRepository.delete(character);

    }

    //Função de encontrar personagem por ID.
    @Override
    public Character findById(Long id) {
        if(!characterRepository.existsById(id)) {
            throw new IllegalArgumentException("O usuário não foi encontrado.");
        }

        return characterRepository.findById(id).orElse(null);

    }

    //Função para retornar todos os personagens.
    @Override
    public List<Character> findAll() {

        return characterRepository.findAll();

    }

    //Função para encontrar personagem por nome.
    @Override
    public List<Character> findByName(String name) {

        return characterRepository.findByName(name);
    }

    //Função para encontrar personagem por raça.
    @Override
    public List<Character> findByRaca(String raca) {

        return characterRepository.findByRaca(raca);
    }

}