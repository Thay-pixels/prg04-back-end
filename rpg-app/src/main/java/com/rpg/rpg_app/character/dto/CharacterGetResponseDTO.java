package com.rpg.rpg_app.character.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterGetResponseDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "raca")
    private String raca;

}
