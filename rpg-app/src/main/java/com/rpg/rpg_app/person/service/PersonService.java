package com.rpg.rpg_app.person.service;

import com.rpg.rpg_app.infrastructure.exception.BusinessException;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.person.entity.Person;
import com.rpg.rpg_app.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements PersonIService {

    private final PersonRepository personRepository;

    //Função para salvar um objeto PEssoa.
    @Override
    @Transactional
    public Person save(Person person) {
        try {
            return personRepository.save(person);

        } catch (BusinessException e) {
            throw new BusinessException("Houve um erro ao salvar objeto Pessoa.");
        }

    }

    //Função para modificar um objeto Pessoa.
    @Override
    @Transactional
    public Person update(Person person) {
        //Tentativa de achar o mestre por id e já lançando a exceção caso nao ache.
        personRepository.findById(person.getId())
                .orElseThrow(() -> new BusinessException("Houve um erro ao buscar o objeto Pessoa par atualizar."));

        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new BusinessException("Houve um erro ao atualizar o objeto Pessoa.");
        }

    }

    //Função para apagar um objeto Pessoa.
    @Override
    @Transactional
    public void delete(Person person) {
        //if para tentar encontrar o objeto, com try e cathc para exceções.
        if(personRepository.existsById(person.getId())){
            try{
                personRepository.delete(person);
            } catch (Exception e) {
                throw new BusinessException("Houve um erro ao deletar o objeto Pessoa.");
            }
        } else {
            throw new BusinessException("Houve um erro ao buscar o objeto Pessoa par exclusão.");
        }
    }

    //Função para retornar um objeto Pessoa por ID.
    @Override
    public Person findById(Long id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Pessoa não encontrada."));

    }

    //Função para retornar todas as pessoas.
    @Override
    public List<Person> findAll() {
        return personRepository.findAll();

    }

    //Função para retornar Pessoa pelo nome.
    @Override
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    //Função para retornar Pessoa pelo email.
    @Override
    public List<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

}
