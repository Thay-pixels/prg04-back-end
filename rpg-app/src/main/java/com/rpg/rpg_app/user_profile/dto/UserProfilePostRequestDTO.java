package com.rpg.rpg_app.user_profile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.rpg_app.user_profile.enums_acess.AcessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilePostRequestDTO {

    @JsonProperty(value = "nivelAcesso")
    private AcessLevel nivelAcesso;

    @JsonProperty(value = "permissions")
    private String permissions;

    @JsonProperty(value = "description")
    private String description;

}
