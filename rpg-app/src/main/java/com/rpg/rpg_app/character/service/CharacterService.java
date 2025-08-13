package com.rpg.rpg_app.character.service;

import com.rpg.rpg_app.character.entity.Character;
import com.rpg.rpg_app.character.repository.CharacterRepository;
import com.rpg.rpg_app.infrastructure.exception.BusinessException;
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
            throw new BusinessException("Os  campos obrigatórios não podem estar vazios.");
        }

    }

    //Função de salvar usuario.
    @Override
    public Character save(Character character) {
        validation(character);
        return characterRepository.save(character);

    }

    //Função de dar update num usuario.
    @Override
    public Character update(Character character) {
        if (character.getId() == null || !characterRepository.existsById(character.getId())) {
            throw new BusinessException("O usuario não foi encontrado para atualizar.");
        }

        validation(character);
        return characterRepository.save(character);

    }

    //Funcao de apagar um usuario.
    @Override
    public void delete(Character character) {
        if (character == null || character.getId() == null) {
            throw new BusinessException("O usuário não foi econtrado para deletar.");
        }

        characterRepository.delete(character);

    }

    //Função de encontrar personagem por ID.
    @Override
    public Character findById(Long id) {

        return characterRepository.findById(id)
                .orElseThrow( () -> new BusinessException("Personagem não encontrado"));

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