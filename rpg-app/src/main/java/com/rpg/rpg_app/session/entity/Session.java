package com.rpg.rpg_app.session.entity;

/* Classe que representa um objeto Session (Sessão) de um Master (Mestre). */

import com.rpg.rpg_app.infrastructure.entity.PersistenceEntity;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.player.entity.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
public class Session extends PersistenceEntity implements Serializable {

    @Column(name = "title",  nullable = false)
    private String titulo;

    @Column(name = "description",  nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Master master;

    @ManyToMany
    @JoinTable(
            name = "session_player",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> jogadores = new ArrayList<>();

}
