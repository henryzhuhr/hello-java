package com.example.uploadfiledemo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String upload(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException {
        // System.out.println("nickname:" + nickname);
        // System.out.println("photo:" + photo.getOriginalFilename());
        // System.out.println("photo:" + photo.getSize());
        // System.out.println("photo:" + photo.getContentType());
        // System.out.println("photo:" + photo.getName());
        // System.out.println("photo:" + photo.getResource());
        // System.out.println("photo:" + photo.getInputStream());
        // System.out.println("photo:" + photo.getBytes());
        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        return path;
    }

    public void saveFile(MultipartFile photo, String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(path + photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
