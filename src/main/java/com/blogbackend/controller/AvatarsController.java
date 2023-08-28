package com.blogbackend.controller;

import com.blogbackend.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("all")
@RequestMapping("/api/v1/images")
public class AvatarsController {

    @Autowired
    private final AvatarService service;
    @PostMapping("/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable int id) throws IOException {
        String uploadImage = service.uploadAvatar(file, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable("id") int id){
        byte[] imageData = service.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAvatar(@PathVariable int id){
        service.deleteAvatar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Avatar by id " + id + " deleted");
    }

}
