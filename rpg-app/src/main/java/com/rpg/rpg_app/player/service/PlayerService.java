package com.rpg.rpg_app.player.service;

import com.rpg.rpg_app.infrastructure.exception.BusinessException;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.player.entity.Player;
import com.rpg.rpg_app.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService implements PlayerIService {

    private final PlayerRepository playerRepository;

    //Função para salvar o user Player.
    @Override
    @Transactional
    public Player save(Player player) {
        try {
            return  playerRepository.save(player);

        } catch (BusinessException e) {
            throw new BusinessException("Houve um erro ao salvar o Jogador.");
        }

    }

    //Função para modificar o user Player.
    @Override
    @Transactional
    public Player update(Player player) {
        //Tentativa de achar o player por id e já lançando a exceção caso nao ache.
        playerRepository.findById(player.getId())
                .orElseThrow(() -> new BusinessException("Houve um erro ao buscar o Jogador par atualizar."));

        try {
            return playerRepository.save(player);
        } catch (Exception e) {
            throw new BusinessException("Houve um erro ao atualizar o Jogador.");
        }

    }

    //Função para apagar o user Player.
    @Override
    @Transactional
    public void delete(Player player) {
        //if para tentar encontrar o objeto player, com try e cathc para exceções.
        if(playerRepository.existsById(player.getId())){
            try{
                playerRepository.delete(player);
            } catch (Exception e) {
                throw new BusinessException("Houve um erro ao deletar o Jogador.");
            }
        } else {
            throw new BusinessException("Houve um erro ao buscar o Jogador par exclusão.");
        }
    }

    //Função para retornar por ID.
    @Override
    public Player findById(Long id) {

        return playerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Jogador não encotrado."));

    }

    //Função para retornar todos os players.
    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();

    }

    //Função para retornar por nome.
    @Override
    public List<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }

    //Função para retornar por email.
    public List<Player> findByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

}
