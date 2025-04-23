package org.example.Services;

import org.example.Models.Industries;
import org.example.Models.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

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
        try {
            ResponseEntity<Positions[]> response = restTemplate.getForEntity(url, Positions[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex) {
            throw new RuntimeException("Lỗi khi gọi API /positions: " + ex.getMessage(), ex);
        } catch (RestClientException ex) {
            throw new RuntimeException("Lỗi không xác định khi gọi API: " + ex.getMessage(), ex);
        }
    }
}
