package com.rpg.rpg_app.player.repository;

import com.rpg.rpg_app.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    //Busca player pelo seu nome.
    public abstract List<Player> findByName(String name);

    //Busca player pelo email.
    public abstract List<Player> findByEmail(String email);

}
