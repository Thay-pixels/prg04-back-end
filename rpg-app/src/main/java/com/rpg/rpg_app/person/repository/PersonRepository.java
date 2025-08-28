package com.rpg.rpg_app.person.repository;

import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>  {

    //Função para retornar pelo nome.
    public abstract List<Person> findByName(String name);

    //Função para retornar pelo email.
    public abstract List<Person> findByEmail(String email);

}
