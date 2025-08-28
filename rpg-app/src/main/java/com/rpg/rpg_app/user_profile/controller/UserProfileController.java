package com.rpg.rpg_app.user_profile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.rpg_app.infrastructure.mapper.ObjectMapperUtil;
import com.rpg.rpg_app.master.dto.MasterGetResponseDTO;
import com.rpg.rpg_app.master.dto.MasterPostRequestDTO;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.user_profile.dto.UserProfileGetResponseDTO;
import com.rpg.rpg_app.user_profile.dto.UserProfilePostRequestDTO;
import com.rpg.rpg_app.user_profile.entity.UserProfile;
import com.rpg.rpg_app.user_profile.enums_acess.AcessLevel;
import com.rpg.rpg_app.user_profile.service.UserProfileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users-profile")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final ObjectMapperUtil  objectMapperUtil;

    //Encontra todos os perfis de usuário.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserProfileGetResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.userProfileService.findAll(), UserProfileGetResponseDTO.class));
    }

    //Salva o Perfil de Usuário.
    @PostMapping(path = "/save-user-profile", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UserProfilePostRequestDTO userProfilePostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        userProfileService.save(objectMapperUtil.map(userProfilePostRequestDto, UserProfile.class))
                        , MasterGetResponseDTO.class
                ));
    }

    //Deleta o Perfil de Usuário.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            UserProfile userProfile = userProfileService.findById(id); // Verifica se existe
            userProfileService.delete(userProfile); // Executa a deleção
            return ResponseEntity.ok("Perfil de usuário deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil de usuário não encontrado.");
        }
    }

    //Encontra Perfil de Usuário por ID.
    @GetMapping(path = "/find/user-profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    objectMapperUtil.map(userProfileService.findById(id), UserProfileGetResponseDTO.class)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil de usuário não encontrado.");
        }
    }

    //Busca Perfil de Usuário por seu nivel de acesso.
    @GetMapping(path = "/find/user-profile/nivel-acesso/{nivelAcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByNivelAcesso(@PathVariable AcessLevel nivelAcesso) {
        try {
            List<UserProfileGetResponseDTO> usersProfile = objectMapperUtil.mapAll(userProfileService.findByNivelAcesso(nivelAcesso), UserProfileGetResponseDTO.class);
            if (usersProfile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum Perfil de Usuário encontrado com este nível de acesso.");
            }
            return ResponseEntity.ok(usersProfile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Perfil com este nível de acesso.");
        }
    }

    //Busca Perfil de Usuário por suas permissões.
    @GetMapping(path = "/find/user-profile/permissions/{permissions}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByPermissions(@PathVariable String permissions) {
        try {
            List<UserProfileGetResponseDTO> usersProfile = objectMapperUtil.mapAll(userProfileService.findByPermissions(permissions), UserProfileGetResponseDTO.class);
            if (usersProfile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum Perfil de Usuário encontrado com esta permissão.");
            }
            return ResponseEntity.ok(usersProfile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Perfil com esta permissão.");
        }
    }

}
