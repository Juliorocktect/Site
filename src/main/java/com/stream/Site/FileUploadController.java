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
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Define the path where the file will be saved
        Path path = Paths.get("videos/" + file.getOriginalFilename());

        try {
            // Save the file to the defined path
            Files.copy(file.getInputStream(), path);
            renameFile(file.getOriginalFilename(),"NewName");
        } catch (IOException e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
            return "File upload failed";
        }

        return "File uploaded successfully";
    }
    public void renameFile(String oldName, String newName) {
        // Define the old and new file paths
        Path oldPath = Paths.get(oldName);
        Path newPath = Paths.get(newName);

        try {
            // Rename the file
            Files.move(oldPath, newPath);
        } catch (IOException e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }
}
