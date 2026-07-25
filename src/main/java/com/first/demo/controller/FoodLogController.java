package com.first.demo.controller;

import java.util.List;

import com.first.demo.dto.FoodLogResponse;
import com.first.demo.dto.FoodRequest;
import com.first.demo.entity.FoodLog;
import com.first.demo.service.FoodLogService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/foodlog")
@RequiredArgsConstructor
public class FoodLogController {

    private final FoodLogService foodLogService;

    @PostMapping("/analyse")
    public ResponseEntity<String> analyse(
            @RequestBody FoodRequest food) {

        FoodLog foodLog = foodLogService.analyseFood(food);
        String AiResponse = foodLog.toString();

        return ResponseEntity.ok(AiResponse);
    }

    @GetMapping("/last7days")
    public ResponseEntity<List<FoodLogResponse>> getLast7DaysLogs(Authentication authentication) {
        String userId = authentication.getName();
        List<FoodLogResponse> logs = foodLogService.getLast7DaysLogs(userId);
        return ResponseEntity.ok(logs);
    }
}

