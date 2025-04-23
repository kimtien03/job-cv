package org.example.Services;

import org.example.Models.Industries;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class IndustryService {
    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String apiBaseUrl;

    public IndustryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Industries> getAllIndustries() {
        String url = apiBaseUrl + "/industries";
        try {
            ResponseEntity<Industries[]> response = restTemplate.getForEntity(url, Industries[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex) {
            throw new RuntimeException("Lỗi khi gọi API /industries: " + ex.getMessage(), ex);
        } catch (RestClientException ex) {
            throw new RuntimeException("Lỗi không xác định khi gọi API: " + ex.getMessage(), ex);
        }
    }
}
