package com.first.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "exerciseLogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseLog {

    @Id
    private String id;
    private String userId;
    private String exerciseName;
    private Integer durationMinutes;
    private Double caloriesBurned;
    private String aiAnalysis;
    private LocalDateTime loggedAt;
}
