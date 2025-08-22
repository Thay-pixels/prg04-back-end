package com.rpg.rpg_app.rpgcharacter.repository;

import com.rpg.rpg_app.rpgcharacter.entity.RpgCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RpgCharacterRepository extends JpaRepository<RpgCharacter, Long> {

    //Busca personagem pelo nome.
    List<RpgCharacter> findByName(String name);

    //Busca personagem pela raça.
    List<RpgCharacter> findByRaca(String raca);

}