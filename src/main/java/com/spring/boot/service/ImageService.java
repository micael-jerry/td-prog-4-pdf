package com.spring.boot.service;

import com.spring.boot.model.Image;
import com.spring.boot.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {
    private ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }
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
}
