package com.rpg.rpg_app.character.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //ID único do personagem.

    @Column(name = "nomes")
    @Getter @Setter private String name; //Nome do personagem.

    @Column(name = "racas")
    @Getter @Setter private String raca; //Raça do personagem.

}
