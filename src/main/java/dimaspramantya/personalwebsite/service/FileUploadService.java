package dimaspramantya.personalwebsite.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileUploadService {
    public Map<?, ?> uploadUserPicture(MultipartFile file);
    public Map<?, ?> uploadUserAchievement(MultipartFile file);
}
