package com.blogbackend.repository;

import com.blogbackend.model.AvatarData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<AvatarData, Integer> {
}
