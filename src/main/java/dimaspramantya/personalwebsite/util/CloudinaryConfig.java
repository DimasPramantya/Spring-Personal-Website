package dimaspramantya.personalwebsite.util;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary_cloud_name}")
    private String cloud_name;

    @Value("${cloudinary_api_key}")
    private  String api_key;

    @Value("${cloudinary_api_secret}")
    private String api_secret;

    @Bean
    public Cloudinary cloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", cloud_name);
        config.put("api_key", api_key);
        config.put("api_secret", api_secret);
        Cloudinary cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
