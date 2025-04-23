package org.example.Services;

import org.example.Models.Positions;
import org.example.Models.Styles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.Arrays;
import java.util.List;

@Service
public class StyleService {
    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String apiBaseUrl;

    public StyleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Styles> getAllStyles() {
        String url = apiBaseUrl + "/styles";
        try {
            ResponseEntity<Styles[]> response = restTemplate.getForEntity(url, Styles[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex) {
            throw new RuntimeException("Lỗi khi gọi API /styles: " + ex.getMessage(), ex);
        } catch (RestClientException ex) {
            throw new RuntimeException("Lỗi không xác định khi gọi API: " + ex.getMessage(), ex);
        }
    }
}
