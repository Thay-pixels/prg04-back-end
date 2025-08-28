package com.rpg.rpg_app.session.controller;

import com.rpg.rpg_app.infrastructure.mapper.ObjectMapperUtil;
import com.rpg.rpg_app.master.dto.MasterGetResponseDTO;
import com.rpg.rpg_app.session.dto.SessionGetResponseDTO;
import com.rpg.rpg_app.session.dto.SessionPostRequestDTO;
import com.rpg.rpg_app.session.entity.Session;
import com.rpg.rpg_app.session.service.SessionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SessionController {

    private final SessionService sessionService;
    private final ObjectMapperUtil objectMapperUtil;

    //Encontra todas as sessões.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SessionGetResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.sessionService.findAll(), SessionGetResponseDTO.class));
    }

    //Salva uma Sessão.
    @PostMapping(path = "/save-session", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid SessionPostRequestDTO sessionPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        sessionService.save(objectMapperUtil.map(sessionPostRequestDto, Session.class))
                        , SessionGetResponseDTO.class
                ));
    }

    //Deleta Sessão.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Session session = sessionService.findById(id); // Verifica se existe
            sessionService.delete(session); // Executa a deleção
            return ResponseEntity.ok("Mestre deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mestre não encontrado.");
        }
    }

    //Busca Sessão por ID.
    @GetMapping(path = "/find/session/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    objectMapperUtil.map(sessionService.findById(id), SessionGetResponseDTO.class)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mestre não encontrado.");
        }
    }

    //Busca Sessão pelo título.
    @GetMapping(path = "/find/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByTitle(@PathVariable String title) {
        try {
            List<SessionGetResponseDTO> sessions = objectMapperUtil.mapAll(sessionService.findByTitle(title), SessionGetResponseDTO.class);
            if (sessions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma sessão encontrada com esse título.");
            }
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar sessões com este nome.");
        }
    }

    //Busca Sessão pelo nome do Mestre.
    @GetMapping(path = "/find/session/master-name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByMasterName(@PathVariable String name) {
        try {
            List<SessionGetResponseDTO> sessions = objectMapperUtil.mapAll(sessionService.findByMasterName(name), SessionGetResponseDTO.class);
            if (sessions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma sessão encontrada para este mestre.");
            }
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar sessões com este mestre.");
        }
    }

    //Busca Sessão pelo ID do Mestre.
    @GetMapping(path = "/find/session/master-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByMasterId(@PathVariable Long id) {
        try {
            List<SessionGetResponseDTO> sessions = objectMapperUtil.mapAll(sessionService.findByMasterId(id), SessionGetResponseDTO.class);
            if (sessions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma sessão encontrada para este mestre.");
            }
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar sessões com este mestre.");
        }
    }

    //Busca Sessão pelo nome do Jogador.
    @GetMapping(path = "/find/session/player-name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByPlayerName(@PathVariable String name) {
        try {
            List<SessionGetResponseDTO> sessions = objectMapperUtil.mapAll(sessionService.findByPlayerName(name), SessionGetResponseDTO.class);
            if (sessions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma sessão encontrada para este jogador.");
            }
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar sessões com este jogador.");
        }
    }

    //Busca Sessão pelo  ID do Jogador.
    @GetMapping(path = "/find/session/player-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByPlayerId(@PathVariable Long id) {
        try {
            List<SessionGetResponseDTO> sessions = objectMapperUtil.mapAll(sessionService.findByPlayerId(id), SessionGetResponseDTO.class);
            if (sessions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma sessão encontrada para este jogador.");
            }
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar sessões com este jogador.");
        }
    }

}
