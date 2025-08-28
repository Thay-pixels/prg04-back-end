package com.rpg.rpg_app.rpgcharacter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpgCharacterPostRequestDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "characterClass")
    private String characterClass;

    @JsonProperty(value = "age")
    private int age;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "owner")
    private String owner;

}
