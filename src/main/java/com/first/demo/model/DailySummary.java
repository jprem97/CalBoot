package com.first.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DailySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User user;

    private LocalDate date;

    private Integer calorieGoal;

    private Double caloriesConsumed;

    private Double caloriesBurned;

    private Double proteinConsumed;

    private Double carbsConsumed;

    private Double fatsConsumed;

    private Double fiberConsumed;
}
