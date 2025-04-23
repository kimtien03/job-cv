package org.example.Services;

import org.example.Models.PaginatedResponse;
import org.example.Models.Template_cvs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TemplateCvService {
    private final RestTemplate restTemplate;
    @Value("${api.base-url}")
    private String apiBaseUrl;
    private static final int TEMPLATES_PER_PAGE = 6;
    public TemplateCvService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaginatedResponse getTemplateCvs(Integer industryId, Integer positionId, Integer styleId, Integer page) {
        StringBuilder urlBuilder = new StringBuilder(apiBaseUrl + "/template-cvs");
        List<String> params = new ArrayList<>();
        if (industryId != null) {
            params.add("industryId=" + industryId);
        }
        if (positionId != null) {
            params.add("positionId=" + positionId);
        }
        if (styleId != null) {
            params.add("styleId=" + styleId);
        }
        if (page != null) {
            params.add("page=" + page);
        }
        if (!params.isEmpty()) {
            urlBuilder.append("?").append(String.join("&", params));
        }
        String url = urlBuilder.toString();
        try {
            ParameterizedTypeReference<PaginatedResponse> responseType =
                    new ParameterizedTypeReference<PaginatedResponse>() {};

            ResponseEntity<PaginatedResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    responseType
            );
            return response.getBody();
        } catch (HttpClientErrorException.NotFound ex) {
            return new PaginatedResponse();
        }
    }
}
