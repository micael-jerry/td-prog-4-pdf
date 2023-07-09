package com.spring.boot.controller;

import com.spring.boot.model.Image;
import com.spring.boot.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ImageController {
    private ImageService imageService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Image> list = imageService.findAll();
        model.addAttribute("list", list);
        return "image";
    }

    @GetMapping("/image-show")
    public void showImage(@Param("id") Integer id, HttpServletResponse response) throws IOException {
        Optional<Image> image = imageService.findById(id);
        response.setContentType("image/*");
        response.getOutputStream().write(image.get().getContent());
        response.getOutputStream().close();
    }

    @PostMapping("/image-upload")
    public String imageUpload(@RequestParam("image") MultipartFile image, Model model) throws IOException {
        imageService.save(image);
//        success
        model.addAttribute("success", "File Uploaded Successfully!!!");
        return "image";
    }
}
