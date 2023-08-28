package com.blogbackend.controller;


import com.blogbackend.dto.UserDTO;
import com.blogbackend.exceptions.PersonDoNotCreatedException;
import com.blogbackend.model.User;
import com.blogbackend.model.token.Token;
import com.blogbackend.repository.TokenRepository;
import com.blogbackend.service.AvatarService;
import com.blogbackend.service.UserService;

import com.blogbackend.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class PeopleController {

    private final UserService service;
    private final AvatarService avatarService;
    private final TokenRepository tokenRepository;


    @GetMapping()
    public List<UserDTO> showAll(){
        return service.findAllPeople().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<?> getAvatar(@PathVariable("id") int id) throws NotFoundException {
        User user = service.findOneUser(id);
        byte[] imageData = avatarService.downloadImage(user.getAvatar());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findOneUser(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("People with id " + id +" not found");
        }
    }



    @PostMapping()
    public String createPerson(@RequestBody UserDTO personDTO, BindingResult bindingResult) throws PersonDoNotCreatedException, IOException {
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError error: errorList){
                stringBuilder.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new PersonDoNotCreatedException(stringBuilder.toString());
        }

        service.saveUser(convertToUser(personDTO));
        return "Person added to database successfully";
    }


    @ExceptionHandler
    public ResponseEntity<?> handler(PersonDoNotCreatedException e){
//        PeopleErrorsResponse peopleErrorsResponse = new PeopleErrorsResponse(
//                e.getMessage()
//        );

        return new ResponseEntity<>("peopleErrorsResponse", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handler(NotFoundException e){


        return new ResponseEntity<>("peopleErrorsResponse", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) throws NotFoundException {
//        Optional<Token> foundedToken = tokenRepository.findById();
//        assert foundedToken.orElse(null) != null;
        tokenRepository.delete(service.findOneUser(id).getTokens().get(0));
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully");
    }

    public User convertToUser(UserDTO personDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(personDTO, User.class); // From Person to PersonDTO
    }

    public UserDTO convertToUserDTO(User person){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(person, UserDTO.class); // From Person to PersonDTO

    }

}