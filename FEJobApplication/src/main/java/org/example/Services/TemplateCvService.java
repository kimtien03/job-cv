package org.example.Services;

import org.example.Models.Styles;
import org.example.Models.Template_cvs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.Arrays;
import java.util.List;

@Service
public class TemplateCvService {
    private final RestTemplate restTemplate;
    @Value("${api.base-url}")
    private String apiBaseUrl;

    public TemplateCvService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Template_cvs> getAllTemplateCvs() {
        String url = apiBaseUrl + "/template-cvs";
        try {
            ResponseEntity<Template_cvs[]> response = restTemplate.getForEntity(url, Template_cvs[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex) {
            throw new RuntimeException("Lỗi khi gọi API /template-cvs: " + ex.getMessage(), ex);
        } catch (RestClientException ex) {
            throw new RuntimeException("Lỗi không xác định khi gọi API: " + ex.getMessage(), ex);
        }
    }
    public List<Template_cvs> getTemplateCvsByFilter(Integer positionId, Integer styleId) {
        StringBuilder urlBuilder = new StringBuilder(apiBaseUrl + "/template-cvs/filter?");
        if (positionId != null) {
            urlBuilder.append("positionId=").append(positionId);
        }
        if (styleId != null) {
            if (urlBuilder.charAt(urlBuilder.length() - 1) != '?') {
                urlBuilder.append("&");
            }
            urlBuilder.append("styleId=").append(styleId);
        }
        String url = urlBuilder.toString();
        try {
            ResponseEntity<Template_cvs[]> response = restTemplate.getForEntity(url, Template_cvs[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex) {
            throw new RuntimeException("Lỗi khi gọi API /templates-cvs/filter: " + ex.getMessage(), ex);
        } catch (RestClientException ex) {
            throw new RuntimeException("Lỗi không xác định khi gọi API: " + ex.getMessage(), ex);
        }
    }
}
