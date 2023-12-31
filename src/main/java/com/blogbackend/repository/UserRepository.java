package com.blogbackend.repository;

import java.util.List;
import java.util.Optional;

import com.blogbackend.model.User;
import com.blogbackend.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
//  User findByTokens(List<Token> tokens);

}
