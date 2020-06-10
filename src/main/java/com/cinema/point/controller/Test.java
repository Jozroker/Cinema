package com.cinema.point.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class Test {

    public static String picture;

    @GetMapping("/savefile")
    public String save() {
        return "test";
    }

    @PostMapping("/savefile")
    public String upload(@RequestParam("file") MultipartFile file,
                         Model model) throws IOException {
        byte[] bytes = file.getBytes();
        byte[] encodeBase64 = Base64.encodeBase64(bytes);
        picture = new String(encodeBase64, StandardCharsets.UTF_8);
        model.addAttribute("image", picture);
        return "picture";
    }

    @GetMapping("/picture")
    public String picture(Model model) {

        return "picture";
    }

}
