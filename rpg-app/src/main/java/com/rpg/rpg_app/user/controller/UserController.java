package com.rpg.rpg_app.user.controller;

import com.rpg.rpg_app.character.entity.Character;
import com.rpg.rpg_app.user.entity.User;
import com.rpg.rpg_app.user.repository.UserRepository;
import com.rpg.rpg_app.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users-rpg")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
    //Funções de teste de retorno e funcionamento da api.
    @GetMapping
    public String printUsername(String username) {
        return userService.printUserName("usuario01");
    }

    @PostMapping
    public String addUser(@RequestBody User user) {
        return "Hello "  + user.getUsername();
    }

    @GetMapping(path = "findall")
    public List<User> getAllUsers(){

        return  userService.findAll();
    }

     */

    //Salva usuario.
    @PostMapping(path = "/save-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody User user) {

        try {
            userService.save(user);
            return ResponseEntity.ok("Usuário salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar: " + e.getMessage());
        }

    }

    //Deleta usuario.
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

    //Encontra todos os usuarios.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    //Encontra usuario por ID.
    @GetMapping(path = "/find/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    //Encontra usuario por username.
    @GetMapping(path = "/find/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        try {
            List<User> user = userService.findByUsername(username);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum personagem encontrado com esse nome.");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar personagem com este nome.");
        }
    }

    //Encontra usuario por username.
    @GetMapping(path = "/find/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            List<User> user = userService.findByEmail(email);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum personagem encontrado com esse nome.");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar personagem com este nome.");
        }
    }

}
