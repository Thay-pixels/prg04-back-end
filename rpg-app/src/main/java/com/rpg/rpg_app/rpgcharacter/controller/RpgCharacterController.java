package com.rpg.rpg_app.rpgcharacter.controller;

import com.rpg.rpg_app.rpgcharacter.dto.RpgCharacterGetResponseDTO;
import com.rpg.rpg_app.rpgcharacter.dto.RpgCharacterPostRequestDTO;
import com.rpg.rpg_app.rpgcharacter.entity.RpgCharacter;
import com.rpg.rpg_app.rpgcharacter.service.RpgCharacterService;
import com.rpg.rpg_app.infrastructure.mapper.ObjectMapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character-rpg")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RpgCharacterController {

    private final RpgCharacterService characterService;
    private final ObjectMapperUtil objectMapperUtil;

    //Encontra todos os personagens.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RpgCharacterGetResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.characterService.findAll(), RpgCharacterGetResponseDTO.class));
    }

    //Salva personagem.
    @PostMapping(path = "/save-character", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid RpgCharacterPostRequestDTO rpgCharacterPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        characterService.save(objectMapperUtil.map(rpgCharacterPostRequestDto, RpgCharacter.class))
                        , RpgCharacterGetResponseDTO.class
                ));
    }

    //Deleta personagem.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            RpgCharacter rpgCharacter = characterService.findById(id); // Verifica se existe
            characterService.delete(rpgCharacter); // Executa a deleção
            return ResponseEntity.ok("Personagem deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }
    }

    //Encontra personagem por ID.
    @GetMapping(path = "/find/character/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    objectMapperUtil.map(characterService.findById(id), RpgCharacterGetResponseDTO.class)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }
    }

    //Encontra personagem por nome.
    @GetMapping(path = "/find/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByName(@PathVariable String name) {
        try {
            List<RpgCharacterGetResponseDTO> characters = objectMapperUtil.mapAll(characterService.findByName(name), RpgCharacterGetResponseDTO.class);
            if (characters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum personagem encontrado com esse nome.");
            }
            return ResponseEntity.ok(characters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar personagem com este nome.");
        }
    }

    //Encontra personagem por raça.
    @GetMapping(path = "/find/raca/{raca}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByRaca(@PathVariable String raca) {
        try {
            List<RpgCharacterGetResponseDTO> characters = objectMapperUtil.mapAll(characterService.findByRaca(raca), RpgCharacterGetResponseDTO.class);
            if (characters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum personagem encontrado com essa raça.");
            }
            return ResponseEntity.ok(characters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar personagem com esta raça.");
        }
    }

}
