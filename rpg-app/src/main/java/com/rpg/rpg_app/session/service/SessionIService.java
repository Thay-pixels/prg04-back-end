package com.rpg.rpg_app.session.service;


import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.session.entity.Session;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SessionIService {

    //Função de salvar uma Session .
    public abstract Session save (Session session);

    //Função para modificar uma Session .
    public abstract Session update(Session session);

    //Funcao de apagar uma Session .
    public abstract void delete(Session session);

    //Função de encontrar uma Session por ID.
    public Session findById(Long id);

    //Função para retornar todas as sessões.
    public abstract List<Session> findAll();

    //Busca sessão pelo titulo.
    public abstract List<Session> findByTitle(String title);

    //Busca sessão pelo nome do mestre.
    public abstract List<Session> findByMasterName(String name);

    //Busca sessão pelo ID do mestre.
    public abstract List<Session> findByMasterId(Long id);

    //Busca sessão por um nome de jogador.
    public abstract List<Session> findByPlayerName(String name);

    //Busca sessão pelo ID do jogador.
    public abstract List<Session> findByPlayerId(Long id);

}
