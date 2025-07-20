package com.rpg.rpg_app.user.controller;

import com.rpg.rpg_app.user.entity.User;
import com.rpg.rpg_app.user.repository.UserRepository;
import com.rpg.rpg_app.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-rpg")
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

     */

    @GetMapping
    public List<User> getAllUsers(){
        return  userService.findAll();
    }


}
