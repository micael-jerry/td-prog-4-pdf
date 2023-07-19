package com.spring.boot.service;

import com.spring.boot.model.Image;
import com.spring.boot.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {
    private ImageRepository imageRepository;

    public Optional<Image> findById(Integer id) {
        return imageRepository.findById(id);
    }

    public Image save(MultipartFile image) throws IOException {
        Image imageUploaded = new Image();
        imageUploaded.setName(image.getOriginalFilename());
        imageUploaded.setSize(image.getSize());
        imageUploaded.setContent(image.getBytes());
        return imageRepository.save(imageUploaded);
    }

    public Image update(Integer id, MultipartFile image) throws IOException {
        if (image.getBytes().length != 0) {
            if (id != null) {
                Image imageUpdated = new Image();
                imageUpdated.setId(id);
                imageUpdated.setName(image.getOriginalFilename());
                imageUpdated.setSize(image.getSize());
                imageUpdated.setContent(image.getBytes());
                return imageRepository.save(imageUpdated);
            } else {
                return this.save(image);
            }
        }
        Image image1 = new Image();
        image1.setId(id);
        return image1;
    }
}
