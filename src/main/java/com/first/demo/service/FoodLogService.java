package com.first.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.demo.dto.AiReq;
import com.first.demo.dto.FoodLogResponse;
import com.first.demo.dto.FoodRequest;
import com.first.demo.entity.FoodLog;
import com.first.demo.repo.FoodLogRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodLogService {
    private final AiService aiService;
    private final FoodLogRepository foodLogRepository;
    private final ObjectMapper objectMapper;

    public FoodLog analyseFood(FoodRequest food) {

        AiReq aiReq = new AiReq("food", food.getFood());

        String rawResponse = aiService.callAiApi(aiReq);

        String content = aiService.parseResponse(rawResponse);

        try {
            FoodLog foodLog = objectMapper.readValue(content, FoodLog.class);
            return foodLogRepository.save(foodLog);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse AI response into FoodLog", e);
        }
    }

    @Cacheable(value = "foodlog:last7days", key = "#userId")
    public List<FoodLogResponse> getLast7DaysLogs(String userId) {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        LocalDateTime now = LocalDateTime.now();

        return foodLogRepository.findByUserIdAndLoggedAtBetween(userId, sevenDaysAgo, now)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private FoodLogResponse toResponse(FoodLog foodLog) {
        return new FoodLogResponse(
                foodLog.getMealType(),
                foodLog.getUserInput(),
                foodLog.getAiResponse(),
                foodLog.getLoggedAt()
        );
    }
}
