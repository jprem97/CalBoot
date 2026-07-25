package com.first.demo.dto;

import java.time.LocalDateTime;

import com.first.demo.entity.MealType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodLogResponse {
    private MealType mealType;
    private String userInput;
    private String aiResponse;
    private LocalDateTime loggedAt;
}
