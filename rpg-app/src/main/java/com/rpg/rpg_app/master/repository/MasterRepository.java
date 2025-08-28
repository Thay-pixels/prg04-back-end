package com.rpg.rpg_app.master.repository;

import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.rpgcharacter.entity.RpgCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {

    //Busca mestre pelo nome.
    public abstract List<Master> findByName(String name);

}
