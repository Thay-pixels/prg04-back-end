package com.rpg.rpg_app.user_profile.repository;

import com.rpg.rpg_app.user_profile.entity.UserProfile;
import com.rpg.rpg_app.user_profile.enums_acess.AcessLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    //Busca perfil por nível de acesso.
    public abstract List<UserProfile> findByNivelAcesso(AcessLevel nivelAcesso);

    //Busca perfil pelas permissões.
    public abstract List<UserProfile> findByPermissions(String permissions);

}
