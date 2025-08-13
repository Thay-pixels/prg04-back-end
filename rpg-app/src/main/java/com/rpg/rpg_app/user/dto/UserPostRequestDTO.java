package com.rpg.rpg_app.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostRequestDTO {

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

}
