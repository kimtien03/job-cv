package org.example.Services;

import org.example.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class JobApiService {

    private final RestTemplate restTemplate;

    @Value("${api.base-url}") // bạn có thể set ở application.properties
    private String apiBaseUrl;

    public JobApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        String url =   "http://localhost:8090/api/User/GetAllUser";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        return Arrays.asList(response.getBody());
    }
}

