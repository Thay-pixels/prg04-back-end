package com.rpg.rpg_app.session.repository;

import com.rpg.rpg_app.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

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
