package com.rpg.rpg_app.master.service;

import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.session.entity.Session;

import java.util.List;

public interface MasterIService {

    //Função de salvar um user do tipo Master.
    public abstract Master save (Master master);

    //Função para modificar um user do tipo Master.
    public abstract Master update(Master master);

    //Funcao de apagar um user do tipo Master.
    public abstract void delete(Master master);

    //Função de encontrar um user do tipo Master por ID.
    public Master findById(Long id);

    //Função para retornar todos os mestres.
    public abstract List<Master> findAll();

    //Função para retornar pelo nome.
    public abstract List<Master> findByName(String name);

    /*
    //Função para retornar por sessão
    public abstract List<Session> findBySession(String sessionsCreated);
    */
}
