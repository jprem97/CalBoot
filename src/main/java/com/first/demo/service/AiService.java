package com.first.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String url;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

    public String askAI(String food) {
        try {
            String prompt = "You are a nutrition expert. Analyze the following food item and provide nutritional information per serving. " +
                    "Return ONLY valid JSON with these exact fields (all values must be numbers, not strings): " +
                    "{\"calories\": number, \"protein\": number, \"carbs\": number, \"fats\": number, \"fiber\": number} " +
                    "Example: For '1 cup of rice', return {\"calories\": 206, \"protein\": 4.3, \"carbs\": 44.5, \"fats\": 0.4, \"fiber\": 0.6} " +
                    "Food: " + food;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> body = new HashMap<>();
            body.put("model", model);
            body.put("messages", List.of(
                    Map.of("role", "user", "content", prompt)));

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    url,
                    request,
                    String.class);

            return response.getBody();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}