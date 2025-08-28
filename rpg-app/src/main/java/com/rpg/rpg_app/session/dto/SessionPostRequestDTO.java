package com.rpg.rpg_app.session.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.player.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionPostRequestDTO {

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "master")
    private Master master;

    @JsonProperty(value = "players")
    private List<Player> players;

}
