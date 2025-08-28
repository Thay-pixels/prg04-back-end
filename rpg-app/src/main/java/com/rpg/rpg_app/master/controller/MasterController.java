package com.rpg.rpg_app.master.controller;

import com.rpg.rpg_app.infrastructure.mapper.ObjectMapperUtil;
import com.rpg.rpg_app.master.dto.MasterGetResponseDTO;
import com.rpg.rpg_app.master.dto.MasterPostRequestDTO;
import com.rpg.rpg_app.master.entity.Master;
import com.rpg.rpg_app.master.service.MasterService;
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
@RequestMapping("/masters")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;
    private final ObjectMapperUtil objectMapperUtil;

    //Encontra todos os mestres.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MasterGetResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.masterService.findAll(), MasterGetResponseDTO.class));
    }

    //Salva mestre.
    @PostMapping(path = "/save-master", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid MasterPostRequestDTO masterPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        masterService.save(objectMapperUtil.map(masterPostRequestDto, Master.class))
                        , MasterGetResponseDTO.class
                ));
    }

    //Deleta mestre.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Master master = masterService.findById(id); // Verifica se existe
            masterService.delete(master); // Executa a deleção
            return ResponseEntity.ok("Mestre deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mestre não encontrado.");
        }
    }

    //Encontra mestre por ID.
    @GetMapping(path = "/find/master/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    objectMapperUtil.map(masterService.findById(id), MasterGetResponseDTO.class)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mestre não encontrado.");
        }
    }

    //Encontra mestre por seu nome.
    @GetMapping(path = "/find/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByName(@PathVariable String name) {
        try {
            List<MasterGetResponseDTO> masters = objectMapperUtil.mapAll(masterService.findByName(name), MasterGetResponseDTO.class);
            if (masters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum mestre encontrado com esse nome.");
            }
            return ResponseEntity.ok(masters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar mestres com este nome.");
        }
    }


}
