package dimaspramantya.personalwebsite.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadImplService implements FileUploadService {
    private Cloudinary cloudinary;
    @Value("${profile_folder}")
    private String profileFolder;

    @Value("${achievements_folder}")
    private String achievementFolder;

    @Autowired
    public FileUploadImplService(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @Override
    public Map<?, ?> uploadUserPicture(MultipartFile file) {
        try {
            Map<String, Object> uploadOptions = new HashMap<>();
            uploadOptions.put("folder", profileFolder);
            return cloudinary.uploader().upload(file.getBytes(), uploadOptions);
        } catch(IOException exc){
            throw new RuntimeException(exc.getMessage());
        }
    }

    @Override
    public Map<?, ?> uploadUserAchievement(MultipartFile file) {
        try {
            Map<String, Object> uploadOptions = new HashMap<>();
            uploadOptions.put("folder", achievementFolder);
            return cloudinary.uploader().upload(file.getBytes(), uploadOptions);
        } catch(IOException exc){
            throw new RuntimeException(exc.getMessage());
        }
    }


}
