package com.first.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.demo.dto.FoodRequest;
import com.first.demo.entity.FoodLog;
import com.first.demo.repo.FoodLogRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodLogService {
    private final AiService aiService;
    private final FoodLogRepository foodLogRepository;
    private final ObjectMapper objectMapper;

    public FoodLog analyseFood(FoodRequest food) {

        String aiResponse = aiService.askAI(food.getFood());

        try {
            JsonNode root = objectMapper.readTree(aiResponse);

            String content = root.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

            content = content.replaceAll("```json\\s*", "")
                    .replaceAll("```\\s*", "")
                    .trim();

            FoodLog foodLog = objectMapper.readValue(content, FoodLog.class);

            return foodLogRepository.save(foodLog);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse AI response", e);
        }
    }
}