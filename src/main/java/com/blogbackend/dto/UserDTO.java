package com.blogbackend.dto;


import com.blogbackend.model.AvatarData;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String password;

    @OneToOne()
    private AvatarData avatarData;
}
