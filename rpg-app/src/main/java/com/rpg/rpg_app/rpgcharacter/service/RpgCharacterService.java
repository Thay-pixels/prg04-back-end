package com.rpg.rpg_app.rpgcharacter.service;

import com.rpg.rpg_app.rpgcharacter.entity.RpgCharacter;
import com.rpg.rpg_app.rpgcharacter.repository.RpgCharacterRepository;
import com.rpg.rpg_app.infrastructure.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RpgCharacterService implements RpgCharacterIService {

    private final RpgCharacterRepository rpgCharacterRepository;

    //Funçao teste para testar a 'api'.
    public String printNameCharacter(String name) {

        return "RpgCharacter " + name;

    }

    //Função para validação de campos.
    private void validation(RpgCharacter rpgCharacter) {
        if (rpgCharacter == null ||
                !StringUtils.hasText(rpgCharacter.getName()) ||
                !StringUtils.hasText(rpgCharacter.getCharacterClass())) {
            throw new BusinessException("Os  campos obrigatórios não podem estar vazios.");
        }

    }

    //Função de salvar usuario.
    @Override
    @Transactional
    public RpgCharacter save(RpgCharacter rpgCharacter) {
        validation(rpgCharacter);
        return rpgCharacterRepository.save(rpgCharacter);

    }

    //Função de dar update num usuario.
    @Override
    @Transactional
    public RpgCharacter update(RpgCharacter rpgCharacter) {
        if (rpgCharacter.getId() == null || !rpgCharacterRepository.existsById(rpgCharacter.getId())) {
            throw new BusinessException("O usuario não foi encontrado para atualizar.");
        }

        validation(rpgCharacter);
        return rpgCharacterRepository.save(rpgCharacter);

    }

    //Funcao de apagar um usuario.
    @Override
    @Transactional
    public void delete(RpgCharacter rpgCharacter) {
        if (rpgCharacter == null || rpgCharacter.getId() == null) {
            throw new BusinessException("O usuário não foi econtrado para deletar.");
        }

        rpgCharacterRepository.delete(rpgCharacter);

    }

    //Função de encontrar personagem por ID.
    @Override
    public RpgCharacter findById(Long id) {

        return rpgCharacterRepository.findById(id)
                .orElseThrow( () -> new BusinessException("Personagem não encontrado"));

    }

    //Função para retornar todos os personagens.
    @Override
    public List<RpgCharacter> findAll() {

        return rpgCharacterRepository.findAll();

    }

    //Função para encontrar personagem por nome.
    @Override
    public List<RpgCharacter> findByName(String name) {

        return rpgCharacterRepository.findByName(name);
    }

    //Função para encontrar personagem por raça.
    @Override
    public List<RpgCharacter> findByRaca(String raca) {

        return rpgCharacterRepository.findByRaca(raca);
    }

}