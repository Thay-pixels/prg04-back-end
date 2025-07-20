package com.rpg.rpg_app.user.repository;

import com.rpg.rpg_app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Busca usuario por username.
    List<User> findByUsername(String username);

    //Busca usuário por email.
    List<User> findByEmail(String email);

}