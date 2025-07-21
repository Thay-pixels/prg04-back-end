package com.rpg.rpg_app.character.controller;

import com.rpg.rpg_app.character.entity.Character;
import com.rpg.rpg_app.character.service.CharacterService;
import com.rpg.rpg_app.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
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
public class CharacterController {

    private final CharacterService characterService;

    //Função simples apenas para testar o retorno da api.
    @GetMapping("/character-name")
    public String nameCharacter(String name) {

        return characterService.printNameCharacter(name);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Character character) {
        try {
            characterService.save(character);
            return ResponseEntity.ok("Personagem salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar personagem: " + e.getMessage());
        }
    }

    //Deleta personagem.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Character character = characterService.findById(id); // Verifica se existe
            characterService.delete(character); // Executa a deleção
            return ResponseEntity.ok("Personagem deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }
    }

    //Encontra todos os personagens.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Character>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findAll());
    }

    //Encontra personagem por ID.
    @GetMapping(path = "/find/character/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Character character = characterService.findById(id);
            return ResponseEntity.ok(character);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("personagem não encontrado.");
        }
    }

    //Encontra personagem por raca.
    @GetMapping(path = "/find/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByName(@PathVariable String name) {
        try {
            List<Character> characters = characterService.findByName(name);
            if (characters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum personagem encontrado com esse nome.");
            }
            return ResponseEntity.ok(characters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar personagem com este nome.");
        }
    }


    //Encontra personagem por raca.
    @GetMapping(path = "/find/raca/{raca}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByRaca(@PathVariable String raca) {
        try {
            List<Character> characters = characterService.findByRaca(raca);
            if (characters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum personagem encontrado com essa raça.");
            }
            return ResponseEntity.ok(characters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar personagem com esta raça.");
        }
    }

}
