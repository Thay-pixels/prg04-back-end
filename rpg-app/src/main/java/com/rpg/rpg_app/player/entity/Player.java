package com.rpg.rpg_app.player.entity;

/*Classe Player é uma subclasse para User, representando um tipo de usuário User porem categoria Jogador. */

import com.rpg.rpg_app.person.entity.Person;
import com.rpg.rpg_app.rpgcharacter.entity.RpgCharacter;
import com.rpg.rpg_app.session.entity.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player extends Person {

    @Column(name = "player_experience")
    private int totalExp;

    //Relaçao onde um jogador tem um ou vários personagens.
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RpgCharacter> characters = new ArrayList<>();

    //Relaçao onde um jogador pode estar em várias sessões.
    @ManyToMany(mappedBy = "players")
    private List<Session> sessions = new ArrayList<>();

}
