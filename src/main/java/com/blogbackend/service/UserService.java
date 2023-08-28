package com.blogbackend.service;

import com.blogbackend.model.User;
import com.blogbackend.repository.AvatarRepository;
import com.blogbackend.repository.UserRepository;
import com.blogbackend.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final AvatarRepository avatarRepository;

    public List<User> findAllPeople(){
        return repository.findAll();
    }

    public User findOneUser(int id) throws NotFoundException {
        Optional<User> foundedUser = repository.findById(id);
        return foundedUser.orElseThrow(() -> new NotFoundException("User with id: " + id + " not found!"));
    }

    public void saveUser(User user) throws IOException {
        repository.save(user);
    }

    public void delete(int id){
        repository.deleteById(id);
    }
}
