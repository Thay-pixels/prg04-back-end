package com.rpg.rpg_app.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //Id único.

    @Column(name = "usernames", unique = true)
    @Getter @Setter private String username; //Username do usuário.

    @Column(name = "passwords",  unique = true, nullable = false)
    @Getter @Setter private String password; //Senha do usuário.

    @Column(name = "emails",  unique = true, nullable = false)
    @Getter @Setter private String email; //Email do usuário.

}
