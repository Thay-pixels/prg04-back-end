package com.rpg.rpg_app.person.entity;

/*SuperClasse abstrata que contém as informações pessoais de um usuário (User) no sistema.
  Cada Usuário (User) deve estar relacionado a uma Pessoa (Person).*/

import com.rpg.rpg_app.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "people")
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends PersistenceEntity implements Serializable {

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @Column(name = "age",  nullable = false)
    protected int age;

}
