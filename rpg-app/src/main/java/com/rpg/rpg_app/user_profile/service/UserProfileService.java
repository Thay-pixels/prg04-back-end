package com.rpg.rpg_app.user_profile.service;

import com.rpg.rpg_app.infrastructure.exception.BusinessException;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.user_profile.entity.UserProfile;
import com.rpg.rpg_app.user_profile.enums_acess.AcessLevel;
import com.rpg.rpg_app.user_profile.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService implements UserProfileIService {

    private final UserProfileRepository userProfileRepository;

    //Função para salvar o perfil de usuário.
    @Override
    @Transactional
    public UserProfile save(UserProfile userProfile) {
        try {
            return  userProfileRepository.save(userProfile);

        } catch (BusinessException e) {
            throw new BusinessException("Houve um erro ao salvar o Perfil de Usuário.");
        }

    }

    //Função para modificar o perfil de usuário.
    @Override
    @Transactional
    public UserProfile update(UserProfile userProfile) {
        //Tentativa de achar o perfil de usuário por id e já lançando a exceção caso nao ache.
        userProfileRepository.findById(userProfile.getId())
                .orElseThrow(() -> new BusinessException("Houve um erro ao buscar o Perfil de Usuário par atualizar."));

        try {
            return userProfileRepository.save(userProfile);
        } catch (Exception e) {
            throw new BusinessException("Houve um erro ao atualizar o Perfil de Usuário.");
        }

    }

    //Função para apagar o perfil de usuário.
    @Override
    @Transactional
    public void delete(UserProfile userProfile) {
        //if para tentar encontrar o objeto USerProfile, com try e cathc para exceções.
        if(userProfileRepository.existsById(userProfile.getId())){
            try{
                userProfileRepository.delete(userProfile);
            } catch (Exception e) {
                throw new BusinessException("Houve um erro ao deletar o Perfil de Usuário.");
            }
        } else {
            throw new BusinessException("Houve um erro ao buscar o Perfil de Usuário par exclusão.");
        }
    }

    //Função para retornar por ID.
    @Override
    public UserProfile findById(Long id) {

        return userProfileRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Perfil de Usuário não encontrado."));

    }

    //Função para retornar todos os perfis de usuário.
    @Override
    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();

    }

    //Função para retornar o perfil pelo nivel de acesso.
    @Override
    public List<UserProfile> findByNivelAcesso(AcessLevel nivelAcesso) {
        return  userProfileRepository.findByNivelAcesso(nivelAcesso);
    }

    //Função para retornar o perfil pelas permiss~oes.
    @Override
    public List<UserProfile> findByPermissions(String permissions) {
        return userProfileRepository.findByPermissions(permissions);
    }

}
