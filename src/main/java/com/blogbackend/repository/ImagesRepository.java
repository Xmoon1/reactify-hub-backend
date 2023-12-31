package com.blogbackend.repository;

import com.blogbackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImagesRepository extends JpaRepository<Image, Integer> {

}
