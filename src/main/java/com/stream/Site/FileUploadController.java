package com.stream.Site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
         if (file.isEmpty()){
             redirectAttributes.addFlashAttribute("Error", "select file to upload");
             return "redirect:/";
         }
         Path path = Paths.get("D:\\IntelliJ\\Site\\Site\\src\\main\\resources\\videos", file.getOriginalFilename());
         Files.write(path,file.getBytes());
         redirectAttributes.addFlashAttribute("success massage", "File upload success, File name : " + file.getOriginalFilename());
         return "redirect:/";
    }
}
