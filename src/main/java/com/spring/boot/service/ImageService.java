package com.spring.boot.service;

import com.spring.boot.model.Image;
import com.spring.boot.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
