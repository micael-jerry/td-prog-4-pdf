package com.spring.boot.controller;

import com.spring.boot.model.Image;
import com.spring.boot.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ImageController {
    private ImageService imageService;

    @GetMapping("/show-image")
    public void showImage(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
        Optional<Image> image = imageService.findById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(image.get().getContent());
        response.getOutputStream().close();
    }

}
