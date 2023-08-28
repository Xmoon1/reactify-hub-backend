package com.blogbackend.service;//package com.blogbackend.services;
//
//
//import com.blogbackend.model.Image;
//import com.blogbackend.model.Post;
//import com.blogbackend.repository.ImagesRepository;
//import com.blogbackend.repository.PostsRepository;
//import com.blogbackend.utils.ImageUtils;
//import com.blogbackend.utils.NotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class PostsService {
//    private final PostsRepository repository;
//    private final ImageService service;
//    private final ImagesRepository irepository;
//
//    public List<Post> findAll(){
//        return repository.findAll();
//    }
//
//    public Post findOne(int id) throws NotFoundException {
//        Optional<Post> foundedPost = repository.findById(id);
//        return foundedPost.orElseThrow(() -> new NotFoundException("Post not found!"));
//    }
//
//    public void addPost(Post post, MultipartFile file,
//                        MultipartFile file2,MultipartFile file3) throws IOException {
//        Image image = irepository.save(Image.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build()
//        );
//        Image image2 = irepository.save(Image.builder()
//                .name(file2.getOriginalFilename())
//                .type(file2.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build()
//        );
//        Image image3 = irepository.save(Image.builder()
//                .name(file3.getOriginalFilename())
//                .type(file3.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build()
//        );
//
//        List<Image> images = new ArrayList<>();
//        images.add(image);
//        images.add(image2);
//        images.add(image3);
//        post.setImages(images);
//
//
//        repository.save(post);
//    }
//
//
//    public void deletePost(int id){
//        repository.deleteById(id);
//    }
//}
