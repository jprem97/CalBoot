package com.first.demo.controller;

import com.first.demo.dto.ExerciseResponse;
import com.first.demo.entity.ExerciseLog;
import com.first.demo.service.ExerciseService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/analyse")
    public ResponseEntity<ExerciseResponse> analyse(@RequestBody String exerciseDescription) {
        ExerciseLog exerciseLog = exerciseService.analyseExercise(exerciseDescription);
        ExerciseResponse response = new ExerciseResponse(exerciseDescription, exerciseLog.getAiAnalysis());
        return ResponseEntity.ok(response);
    }
}
