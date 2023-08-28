package com.blogbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "avatars")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvatarData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "avatar_data")
    private byte[] avatarData;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "avatar")
    private User user;

}
