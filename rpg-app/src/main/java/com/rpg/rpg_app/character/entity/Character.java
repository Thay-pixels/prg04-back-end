package com.rpg.rpg_app.character.entity;


import com.rpg.rpg_app.infrastructure.entity.PersistenceEntity;
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
public class Character extends PersistenceEntity implements Serializable {

    @Column(name = "nomes")
    @Getter @Setter private String name; //Nome do personagem.

    @Column(name = "racas")
    @Getter @Setter private String raca; //Raça do personagem.

}
