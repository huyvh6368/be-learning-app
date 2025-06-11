package web.elearning.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {

    private final Cloudinary cloudinary;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final String[] ALLOWED_CONTENT_TYPES = {"image/jpeg", "image/png"};

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // 1. Validate ảnh
    public void validateImage(MultipartFile file) throws IOException {
        // Kiểm tra kích thước file
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IOException("File size exceeds the maximum limit (10MB)");
        }

        // Kiểm tra loại file
        if (!Arrays.asList(ALLOWED_CONTENT_TYPES).contains(file.getContentType())) {
            throw new IOException("Only JPG/JPEG and PNG images are allowed");
        }
    }

    // 2. Upload ảnh
    public Map<String, String> uploadImage(MultipartFile file) {
        Map<String, String> result = new HashMap<>();

        try {
            validateImage(file); // hàm kiểm tra kích thước, định dạng, v.v.

            Map<String, Object> uploadParams = new HashMap<>();
            uploadParams.put("folder", "spring_uploads");

            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

            result.put("public_id", uploadResult.get("public_id").toString());
            result.put("url", uploadResult.get("url").toString());
            result.put("secure_url", uploadResult.get("secure_url").toString());
            result.put("code", "200");
            result.put("message", "Upload thành công");

        } catch (IOException e) {
            result.put("code", "500");
            result.put("message", "Upload thất bại: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            result.put("code", "400");
            result.put("message", "File không hợp lệ: " + e.getMessage());
        }

        return result;
    }


    // 3. Xóa ảnh
    public void deleteImage(String publicId) throws IOException {
        Map<?, ?> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        System.out.println("Delete result: " + deleteResult);
    }
}