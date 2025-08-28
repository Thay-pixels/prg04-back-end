package com.rpg.rpg_app.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.rpg_app.rpgcharacter.entity.RpgCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPostRequestDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "age")
    private int age;

    @JsonProperty(value = "player_experience")
    private String totalExp;

    @JsonProperty(value = "character")
    private RpgCharacter character;

}
