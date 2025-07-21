package com.rpg.rpg_app.character.controller;

import com.rpg.rpg_app.character.entity.Character;
import com.rpg.rpg_app.character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/all-characters")
    public List<Character> findAll(){

        return  characterService.findAll();
    }

}
