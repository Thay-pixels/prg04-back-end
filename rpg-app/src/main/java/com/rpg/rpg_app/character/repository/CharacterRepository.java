package com.rpg.rpg_app.character.repository;

import com.rpg.rpg_app.character.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    //Busca personagem pelo nome.
    List<Character> findByName(String name);

    //Busca personagem pela raça.
    List<Character> findByRaca(String raca);

}