package com.rpg.rpg_app.master.entity;

/*Classe Master é uma subclasse de User, representa um tipo de usuário (User) na categoria Mestre (Master).*/

import com.rpg.rpg_app.person.entity.Person;
import com.rpg.rpg_app.session.entity.Session;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "masters")
public class Master extends Person {

    @Column(name = "num_sessions_created")
    private int sessionsCreated;

    //Lista de sessões de um mestre. Um mestre pode ter uma ou muitas sessões.
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessoes = new ArrayList<>();

}
