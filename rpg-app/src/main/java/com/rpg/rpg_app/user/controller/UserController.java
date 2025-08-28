package com.rpg.rpg_app.user.controller;

import com.rpg.rpg_app.infrastructure.mapper.ObjectMapperUtil;
import com.rpg.rpg_app.user.dto.UserGetResponseDTO;
import com.rpg.rpg_app.user.dto.UserPostRequestDTO;
import com.rpg.rpg_app.user.entity.User;
import com.rpg.rpg_app.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ObjectMapperUtil objectMapperUtil;

    //Encontra todos os usuários.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserGetResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.userService.findAll(), UserGetResponseDTO.class));
    }

    //Salva usuário.
    @PostMapping(path = "/save-user", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UserPostRequestDTO userPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        userService.save(objectMapperUtil.map(userPostRequestDto, User.class)
                        ), UserGetResponseDTO.class
                ));
    }

    //Deleta usuário.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            User user = userService.findById(id); // Verifica se existe
            userService.delete(user); // Executa a deleção
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    //Encontra usuário por ID.
    @GetMapping(path = "/find/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    objectMapperUtil.map(userService.findById(id), UserGetResponseDTO.class)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    //Encontra usuário por seu username.
    @GetMapping(path = "/find/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        try {
            List<UserGetResponseDTO> users = objectMapperUtil.mapAll(userService.findByUsername(username), UserGetResponseDTO.class);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado com esse nome.");
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuário com este nome.");
        }
    }

    //Encontra usuário por seu email.
    @GetMapping(path = "/find/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            List<UserGetResponseDTO> users = objectMapperUtil.mapAll(userService.findByEmail(email), UserGetResponseDTO.class);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado com esse email.");
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuário com este email.");
        }
    }

}