package tp4.enit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tp4.enit.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {

    @Autowired
    private UserService userService;
    @CrossOrigin(origins="*", allowedHeaders = "*")
    @PostMapping("/{username}") // Change pour utiliser le nom d'utilisateur dans l'URL
    public ResponseEntity<?> uploadImage(@PathVariable String username, @RequestParam("image") MultipartFile file) {
        System.out.println("Uploading image for user: " + username);
        try {
            byte[] imageBytes = file.getBytes();
            userService.saveUserImage(username, imageBytes);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Image uploaded successfully");
            return ResponseEntity.ok(response);        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }
}
