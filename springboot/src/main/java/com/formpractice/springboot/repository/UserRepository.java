package com.formpractice.springboot.repository;

import com.formpractice.springboot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsernameAndPassword(String username, String password);

    Optional<UserModel> findByUsername(String username);
}
