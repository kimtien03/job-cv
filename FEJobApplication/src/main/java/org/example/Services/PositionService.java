package org.example.Services;

import org.example.Models.Industries;
import org.example.Models.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PositionService {
    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String apiBaseUrl;

    public PositionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Positions> getAllPositions() {
        String url = apiBaseUrl + "/positions";
        ResponseEntity<Positions[]> response = restTemplate.getForEntity(url, Positions[].class);
        return Arrays.asList(response.getBody());
    }
}
