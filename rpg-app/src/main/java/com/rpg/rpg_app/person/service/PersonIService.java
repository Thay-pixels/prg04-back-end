package com.rpg.rpg_app.person.service;

import com.rpg.rpg_app.person.entity.Person;

import java.util.List;

public interface PersonIService {

    //Função de salvar um objeto do tipo Pessoa.
    public abstract Person save (Person person);

    //Função para modificar um objeto do tipo Pessoa.
    public abstract Person update(Person person);

    //Funcao de apagar um objeto do tipo Pessoa.
    public abstract void delete(Person person);

    //Função de encontrar um um objeto do tipo Pessoa por ID.
    public Person findById(Long id);

    //Função para retornar todas as pessoas.
    public abstract List<Person> findAll();

    //Função para retornar pelo nome.
    public abstract List<Person> findByName(String name);

    //Função para retornar pelo email.
    public abstract List<Person> findByEmail(String email);

}
