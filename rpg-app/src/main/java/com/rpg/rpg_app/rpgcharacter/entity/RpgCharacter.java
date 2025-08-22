package com.rpg.rpg_app.rpgcharacter.entity;

/*Classe que contem atributos necessários para um RpgCharacter (Personagem), que será criado por um Player (Jogador).*/

import com.rpg.rpg_app.infrastructure.entity.PersistenceEntity;
import com.rpg.rpg_app.player.entity.Player;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
@EqualsAndHashCode(callSuper = true)
public class RpgCharacter extends PersistenceEntity implements Serializable {

    @Column(name = "name",  nullable = false)
    private String name; //Nome do personagem.

    @Column(name = "age",  nullable = false)
    private int age;

    @Column(name = "class",   nullable = false)
    private String characterClass; //CLasse do personagem.

    @Column(name = "description")
    private String description;

    //Relação de Player com RpgCharacter, onde vários personagens podem pertencer a um jogador.
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player owner;

}
