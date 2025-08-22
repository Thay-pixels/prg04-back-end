package com.rpg.rpg_app.person.entity;

/*Classe abstrata que contem as informações pessoais de um usuário (User) no sistema.
  Cada Usuário( User) deve estar relacionado a uma Pessoa (Person).*/

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
@MappedSuperclass
public abstract class Person extends PersistenceEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age",  nullable = false)
    private int age;

}
