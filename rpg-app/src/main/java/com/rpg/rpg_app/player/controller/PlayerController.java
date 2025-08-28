package com.rpg.rpg_app.player.controller;

import com.rpg.rpg_app.infrastructure.mapper.ObjectMapperUtil;
import com.rpg.rpg_app.master.dto.MasterGetResponseDTO;
import com.rpg.rpg_app.master.dto.MasterPostRequestDTO;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.person.dto.PersonGetResponseDTO;
import com.rpg.rpg_app.player.dto.PlayerGetResponseDTO;
import com.rpg.rpg_app.player.dto.PlayerPostRequestDTO;
import com.rpg.rpg_app.player.entity.Player;
import com.rpg.rpg_app.player.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final ObjectMapperUtil objectMapperUtil;

    //Encontra todos os players.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerGetResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.playerService.findAll(), PlayerGetResponseDTO.class));
    }

    //Salva player.
    @PostMapping(path = "/save-player", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid PlayerPostRequestDTO playerPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        playerService.save(objectMapperUtil.map(playerPostRequestDto, Player.class))
                        , PlayerGetResponseDTO.class
                ));
    }

    //Deleta player.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Player player = playerService.findById(id); // Verifica se existe
            playerService.delete(player); // Executa a deleção
            return ResponseEntity.ok("Jogador deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado.");
        }
    }

    //Encontra player por ID.
    @GetMapping(path = "/find/player/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    objectMapperUtil.map(playerService.findById(id), PlayerGetResponseDTO.class)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado.");
        }
    }

    //Encontra player por seu nome.
    @GetMapping(path = "/find/player/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByName(@PathVariable String name) {
        try {
            List<PlayerGetResponseDTO> players = objectMapperUtil.mapAll(playerService.findByName(name), PlayerGetResponseDTO.class);
            if (players.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum jogador encontrado com esse nome.");
            }
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar jogador com este nome.");
        }
    }

    //Encontra player por seu email.
    @GetMapping(path = "/find/player/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            List<PlayerGetResponseDTO> players = objectMapperUtil.mapAll(playerService.findByEmail(email), PlayerGetResponseDTO.class);
            if (players.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum jogador encontrada com este email.");
            }
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar jogador por este email");
        }
    }


}
