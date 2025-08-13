package com.rpg.rpg_app.user.entity;

import com.rpg.rpg_app.infrastructure.entity.PersistenceEntity;
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
    @Getter @Setter private String username; //Username do usuário.

    @Column(name = "passwords",  unique = true, nullable = false)
    @Getter @Setter private String password; //Senha do usuário.

    @Column(name = "emails",  unique = true, nullable = false)
    @Getter @Setter private String email; //Email do usuário.

}
