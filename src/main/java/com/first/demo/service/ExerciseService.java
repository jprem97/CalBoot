package com.first.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.demo.dto.AiReq;
import com.first.demo.entity.ExerciseLog;
import com.first.demo.repo.mongo.ExerciseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final AiService aiService;
    private final ExerciseRepository exerciseRepository;
    private final ObjectMapper objectMapper;

    public ExerciseLog analyseExercise(String exerciseDescription) {

        AiReq aiReq = new AiReq("exercise", exerciseDescription);

        String rawResponse = aiService.callAiApi(aiReq);

        String content = aiService.parseResponse(rawResponse);

        try {
            ExerciseLog exerciseLog = objectMapper.readValue(content, ExerciseLog.class);
            exerciseLog.setLoggedAt(LocalDateTime.now());
            return exerciseRepository.save(exerciseLog);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response into ExerciseLog", e);
        }
    }

    
}
