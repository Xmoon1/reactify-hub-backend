package com.blogbackend.service;


import com.blogbackend.model.AvatarData;
import com.blogbackend.model.User;
import com.blogbackend.repository.AvatarRepository;
import com.blogbackend.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@SuppressWarnings("all")
public class AvatarService {
    private final AvatarRepository repository;
    private final UserService service;
    public String uploadAvatar(MultipartFile file, int id) throws IOException {
        AvatarData data = repository.save(AvatarData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .avatarData(ImageUtils.compressImage(file.getBytes())).build());

        User foundedUser = service.findOneUser(id);
        foundedUser.setAvatar(data);
        service.saveUser(foundedUser);
        if (data!=null){
            return "File uploaded successfully : " + file.getOriginalFilename();
        }else return "File don't uploaded";
    }

    public byte[] downloadImage(int imageId){
        Optional<AvatarData> dbImage = repository.findById(imageId);
        byte[] images = ImageUtils.decompressImage(dbImage.get().getAvatarData());
        return images;
    }

    public void deleteAvatar(int imageId){
        repository.deleteById(imageId);
    }
}
