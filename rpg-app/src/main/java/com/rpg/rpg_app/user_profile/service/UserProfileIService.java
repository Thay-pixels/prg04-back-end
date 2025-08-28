package com.rpg.rpg_app.user_profile.service;

import com.rpg.rpg_app.user_profile.entity.UserProfile;
import com.rpg.rpg_app.user_profile.enums_acess.AcessLevel;

import java.util.List;

public interface UserProfileIService {

    //Função de salvar um perfil.
    public abstract UserProfile save (UserProfile userProfile);

    //Função para modificar um um perfil.
    public abstract UserProfile update(UserProfile userProfile);

    //Funcao de apagar um perfil.
    public abstract void delete(UserProfile userProfile);

    //Função de encontrar um perfil.
    public UserProfile findById(Long id);

    //Função para retornar todos os perfis.
    public abstract List<UserProfile> findAll();

    //Busca perfil por nível de acesso.
    public abstract List<UserProfile> findByNivelAcesso(AcessLevel nivelAcesso);

    //Busca perfil pelas permissões.
    public abstract List<UserProfile> findByPermissions(String permissions);

}
