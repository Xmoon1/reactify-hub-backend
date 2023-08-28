package com.blogbackend.service;//package com.blogbackend.services;
//
//
//import com.blogbackend.model.AvatarData;
//import com.blogbackend.model.Image;
//import com.blogbackend.model.Person;
//import com.blogbackend.model.Post;
//import com.blogbackend.repository.ImagesRepository;
//import com.blogbackend.utils.ImageUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ImageService {
//    private final ImagesRepository repository;
//    private final PostsService postsService;
//
//
//    public String uploadImage(MultipartFile file1, MultipartFile file2, MultipartFile file3,
//                              int id) throws IOException {
//        repositorySave(file1);
//        repositorySave(file2);
//        repositorySave(file3);
//
////        Post foundedPosts = peopleService.findOnePerson(id);
////        foundedPerson.setAvatar(data);
////        peopleService.savePerson(foundedPerson);
////        if (data!=null){
////            return "File uploaded successfully : " + file.getOriginalFilename();
////        }
//        return null;
//    }
//
//
//
//    protected void repositorySave(MultipartFile file) throws IOException {
//        Image image = repository.save(Image.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build()
//        );
//    }
//
//}