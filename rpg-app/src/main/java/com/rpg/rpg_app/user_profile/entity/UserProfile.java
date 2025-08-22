package com.rpg.rpg_app.user_profile.entity;

/*Classe para o tipo de perfil de usuário (ADMIN, MASTER E PLAYER).*/

import com.rpg.rpg_app.infrastructure.entity.PersistenceEntity;
import com.rpg.rpg_app.user_profile.enums_acess.AcessLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_profile")
public class UserProfile extends PersistenceEntity implements Serializable {

    @Column(name = "acess",  nullable = false)
    @Enumerated(EnumType.STRING)
    private AcessLevel nivelAcesso;

    @Column(name = "permissions")
    private String permissoes;

    @Column(name = "description")
    private String descricao;

}
