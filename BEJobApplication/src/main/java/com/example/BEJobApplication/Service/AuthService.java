package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.LoginResponse;
import com.example.BEJobApplication.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.regex.Pattern;

@Service
public class AuthService {

    private static final String API_KEY = "98c148c7a3cd4091a489f0dc1b08cb18";
    private static final String VALIDATION_URL = "https://emailvalidation.abstractapi.com/v1/";

    // Regex kiểm tra định dạng email
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    // ✅ Kiểm tra định dạng email
    public boolean isValidFormat(String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }

    // ✅ Gọi API kiểm tra email thật sự có tồn tại không
    public boolean isEmailDeliverable(String email) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(VALIDATION_URL)
                .queryParam("api_key", API_KEY)
                .queryParam("email", email)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(uri, Map.class);

        if (response == null) {
            return false;
        }

        String deliverability = (String) response.get("deliverability");
        return "DELIVERABLE".equalsIgnoreCase(deliverability);
    }
}
