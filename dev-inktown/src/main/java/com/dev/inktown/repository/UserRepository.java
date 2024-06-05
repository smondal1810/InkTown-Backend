package com.dev.inktown.repository;

import com.dev.inktown.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserPhoneNo(String userPhoneNo);
}
