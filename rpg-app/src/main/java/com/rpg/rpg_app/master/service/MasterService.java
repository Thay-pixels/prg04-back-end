package com.rpg.rpg_app.master.service;

import com.rpg.rpg_app.infrastructure.exception.BusinessException;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.master.repository.MasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterService implements MasterIService {

    private final MasterRepository masterRepository;

    //Função para salvar o user Master.
    @Override
    @Transactional
    public Master save(Master master) {
        try {
            return  masterRepository.save(master);

        } catch (BusinessException e) {
            throw new BusinessException("Houve um erro ao salvar o Mestre.");
        }

    }

    //Função para modificar o user Master.
    @Override
    @Transactional
    public Master update(Master master) {
        //Tentativa de achar o mestre por id e já lançando a exceção caso nao ache.
        masterRepository.findById(master.getId())
                .orElseThrow(() -> new BusinessException("Houve um erro ao buscar o Mestre par atualizar."));

        try {
            return masterRepository.save(master);
        } catch (Exception e) {
            throw new BusinessException("Houve um erro ao atualizar o Mestre.");
        }

    }

    //Função para apagar o user Master.
    @Override
    @Transactional
    public void delete(Master master) {
        //if para tentar encontrar o objeto mestre, com try e cathc para exceções.
        if(masterRepository.existsById(master.getId())){
            try{
                masterRepository.delete(master);
            } catch (Exception e) {
                throw new BusinessException("Houve um erro ao deletar o Mestre.");
            }
        } else {
            throw new BusinessException("Houve um erro ao buscar o Mestre par exclusão.");
        }
    }

    //Função para retornar por ID.
    @Override
    public Master findById(Long id) {

        return masterRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mestre não encotrado."));

    }

    //Função para retornar todos os mestres.
    @Override
    public List<Master> findAll() {
        return masterRepository.findAll();

    }

    //Função para retornar por nome.
    @Override
    public List<Master> findByName(String name) {
        return masterRepository.findByName(name);
    }

}
