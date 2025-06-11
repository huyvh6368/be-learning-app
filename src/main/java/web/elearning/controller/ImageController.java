package web.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.elearning.service.ImageService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = imageService.uploadImage(file);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{publicId}")
    public ResponseEntity<?> deleteImage(@PathVariable String publicId) {
        try {
            imageService.deleteImage(publicId);
            return ResponseEntity.ok().body("Image deleted successfully");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
