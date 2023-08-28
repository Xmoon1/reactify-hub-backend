package com.blogbackend.model;

import com.blogbackend.model.enums.Role;
import com.blogbackend.model.token.Token;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "avatar_id")
  private AvatarData avatar;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Post> post;

  private LocalDateTime dateOfCreated;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Getter
  @OneToMany(mappedBy = "user")
  private List<Token> tokens;


  public void setTokens(List<Token> tokens) {
    this.tokens = tokens;
  }

  @PrePersist
  private void init(){
    dateOfCreated = LocalDateTime.now();
  }

  public int getAvatar() {
    return avatar.getId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
