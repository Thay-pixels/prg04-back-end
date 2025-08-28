package com.rpg.rpg_app.person.controller;


import com.rpg.rpg_app.infrastructure.mapper.ObjectMapperUtil;
import com.rpg.rpg_app.master.dto.MasterGetResponseDTO;
import com.rpg.rpg_app.master.dto.MasterPostRequestDTO;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.person.dto.PersonGetResponseDTO;
import com.rpg.rpg_app.person.dto.PersonPostRequestDTO;
import com.rpg.rpg_app.person.entity.Person;
import com.rpg.rpg_app.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final ObjectMapperUtil objectMapperUtil;

    //Encontra todas as pessoas.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonGetResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.personService.findAll(), PersonGetResponseDTO.class));
    }

    //Salva pessoa.
    @PostMapping(path = "/save-person", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid PersonPostRequestDTO personPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        personService.save(objectMapperUtil.map(personPostRequestDto, Person.class))
                        , MasterGetResponseDTO.class
                ));
    }

    //Deleta pessoa.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Person person = personService.findById(id); // Verifica se existe
            personService.delete(person); // Executa a deleção
            return ResponseEntity.ok("Pessoa deletada com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
    }

    //Encontra pessoa por ID.
    @GetMapping(path = "/find/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    objectMapperUtil.map(personService.findById(id), MasterGetResponseDTO.class)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
    }

    //Encontra pessoa por seu nome.
    @GetMapping(path = "/find/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByName(@PathVariable String name) {
        try {
            List<PersonGetResponseDTO> people = objectMapperUtil.mapAll(personService.findByName(name), PersonGetResponseDTO.class);
            if (people.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma pessoa encontrada com esse nome.");
            }
            return ResponseEntity.ok(people);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar pessoas com este nome.");
        }
    }

    //Encontra pessoa por seu email.
    @GetMapping(path = "/find/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            List<PersonGetResponseDTO> people = objectMapperUtil.mapAll(personService.findByEmail(email), PersonGetResponseDTO.class);
            if (people.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma pessoa encontrada com este email.");
            }
            return ResponseEntity.ok(people);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar pessoas por este email");
        }
    }

}
