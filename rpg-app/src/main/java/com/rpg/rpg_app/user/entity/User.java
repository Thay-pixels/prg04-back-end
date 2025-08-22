package com.rpg.rpg_app.user.entity;

/* Classe que contém o necessário para um User logar em seu perfil.*/

import com.rpg.rpg_app.infrastructure.entity.PersistenceEntity;
import com.rpg.rpg_app.person.entity.Person;
import com.rpg.rpg_app.user_profile.entity.UserProfile;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends PersistenceEntity implements Serializable {

    @Column(name = "usernames", unique = true)
    private String username; //Username do usuário.

    @Column(name = "passwords",  unique = true, nullable = false)
    private String password; //Senha do usuário.

    @Column(name = "emails",  unique = true, nullable = false)
    private String email; //Email do usuário.

    //Relação de um usuário (User) com uma Person (PEssoa)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    //Relacão de um perfil de um usuario, onde vários usuários podem compartilhar o mesmo UserProfile
    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;

}
