package com.rpg.rpg_app.session.service;

import com.rpg.rpg_app.infrastructure.exception.BusinessException;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.session.entity.Session;
import com.rpg.rpg_app.session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService implements SessionIService {

    private final SessionRepository sessionRepository;

    //Função para salvar a Sessão.
    @Override
    @Transactional
    public Session save(Session session) {
        try {
            return  sessionRepository.save(session);

        } catch (BusinessException e) {
            throw new BusinessException("Houve um erro ao salvar a Sessão.");
        }

    }

    //Função para modificar a Sessão.
    @Override
    @Transactional
    public Session update(Session session) {
        //Tentativa de achar o mestre por id e já lançando a exceção caso nao ache.
        sessionRepository.findById(session.getId())
                .orElseThrow(() -> new BusinessException("Houve um erro ao buscar a Sessão para atualizar."));

        try {
            return sessionRepository.save(session);
        } catch (Exception e) {
            throw new BusinessException("Houve um erro ao atualizar a Sessão.");
        }

    }

    //Função para apagar uma Sessão.
    @Override
    @Transactional
    public void delete(Session session) {
        //if para tentar encontrar o objeto Sessão, com try e cathc para exceções.
        if(sessionRepository.existsById(session.getId())){
            try{
                sessionRepository.delete(session);
            } catch (Exception e) {
                throw new BusinessException("Houve um erro ao deletar a Sessão.");
            }
        } else {
            throw new BusinessException("Houve um erro ao buscar a Sessão para exclusão.");
        }
    }

    //Função para retornar por ID.
    @Override
    public Session findById(Long id) {

        return sessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Sessão não encontrada."));

    }

    //Função para retornar todas as sessões.
    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();

    }

    //Função para encontrar Sessão por título.
    @Override
    public List<Session> findByTitle(String title) {
        return sessionRepository.findByTitle(title);

    }

    //Função para encontrar Sessão por nome do Mestre.
    @Override
    public List<Session> findByMasterName(String name) {
        return sessionRepository.findByMasterName(name);

    }

    //Função para encontrar Sessão por ID do Mestre.
    @Override
    public List<Session> findByMasterId(Long id) {
        return sessionRepository.findByMasterId(id);

    }

    //Função para encontrar Sessão por nome do Jogador.
    @Override
    public List<Session> findByPlayerName(String name) {
        return sessionRepository.findByPlayerName(name);

    }

    //Função para encontrar Sessão por ID do jogador.
    @Override
    public List<Session> findByPlayerId(Long id) {
        return sessionRepository.findByPlayerId(id);

    }

}
