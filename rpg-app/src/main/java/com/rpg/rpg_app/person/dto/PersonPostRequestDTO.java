package com.rpg.rpg_app.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPostRequestDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "age")
    private int age;

    @JsonProperty(value = "email")
    private String email;

}
