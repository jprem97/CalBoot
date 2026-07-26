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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.demo.dto.AiReq;

@Service
public class AiService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String url;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String buildFoodPrompt(String food) {
        return "You are a nutrition expert. Analyze the following food item and provide nutritional information per serving. " +
                "Return ONLY valid JSON with these exact fields (all values must be numbers, not strings): " +
                "{\"calories\": number, \"protein\": number, \"carbs\": number, \"fats\": number, \"fiber\": number} " +
                "Example: For '1 cup of rice', return {\"calories\": 206, \"protein\": 4.3, \"carbs\": 44.5, \"fats\": 0.4, \"fiber\": 0.6} " +
                "Food: " + food;
    }

    public String buildExercisePrompt(String exercise) {
        return "You are a fitness expert. Analyze the following exercise and provide workout analysis. " +
                "Return ONLY valid JSON with these exact fields (all values must be numbers except exerciseName and aiAnalysis which are strings): " +
                "{\"exerciseName\": \"string\", \"durationMinutes\": number, \"caloriesBurned\": number, \"aiAnalysis\": \"string\"} " +
                "The aiAnalysis should be a brief summary of the exercise benefits and recommendations. " +
                "Example: For '30 minutes of running', return {\"exerciseName\": \"Running\", \"durationMinutes\": 30, \"caloriesBurned\": 300, \"aiAnalysis\": \"Running is an excellent cardiovascular exercise...\"} " +
                "Exercise: " + exercise;
    }

    public String callAiApi(AiReq aiReq) {
        try {
            String prompt;
            if ("food".equalsIgnoreCase(aiReq.getType())) {
                prompt = buildFoodPrompt(aiReq.getReq());
            } else if ("exercise".equalsIgnoreCase(aiReq.getType())) {
                prompt = buildExercisePrompt(aiReq.getReq());
            } else {
                return "Error: Unknown type '" + aiReq.getType() + "'. Expected 'food' or 'exercise'.";
            }

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

    public String parseResponse(String rawResponse) {
        try {
            JsonNode root = objectMapper.readTree(rawResponse);
            String content = root.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

            content = content.replaceAll("```json\\s*", "")
                    .replaceAll("```\\s*", "")
                    .trim();

            return content;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response", e);
        }
    }
}
