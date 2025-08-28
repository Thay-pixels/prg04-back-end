package com.rpg.rpg_app.master.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.rpg_app.session.entity.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterPostRequestDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "age")
    private int age;

    @JsonProperty(value = "sessionsCreated")
    private int sessionsCreated;

    @JsonProperty(value = "sessoes")
    private List<Session> sessoes;

}
