package com.example.confirmemail.repository;
import java.util.Optional;

import com.example.confirmemail.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}

