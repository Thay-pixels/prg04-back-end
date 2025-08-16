package com.rpg.rpg_app.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostRequestDTO {

    @JsonProperty(value = "username")
    @NotNull(message = "O username é obrigatório!")
    @NotBlank(message = "O nome não pode ser vazio!")
    @Size(min = 5, max = 8, message = "O username precisa ter entre 5 a 8 caracteres!")
    private String username;

    @JsonProperty(value = "email")
    @Email(message = "E-mail inválido!")
    private String email;

    @JsonProperty(value = "password")
    @NotNull(message = "A senha é obrigatória!")
    @NotBlank(message = "A senha não pode ser vazia!")
    @Size(min = 7, max = 12, message = "A senha deve conter de 7 a 12 caracteres!")
    private String password;

}
