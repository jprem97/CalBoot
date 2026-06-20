
package com.first.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum MealType{
    BREAKFAST,
    LUNCH,
    SNACKS,
    DINNER
}
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Column(columnDefinition="TEXT")
    private String userInput;

    private String imageUrl;

    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fats;
    private Double fiber;

    @Column(columnDefinition = "TEXT")
    private String aiResponse;

    private LocalDateTime loggedAt;
}