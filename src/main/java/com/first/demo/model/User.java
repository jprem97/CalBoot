package com.first.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum GoalType {
    LOSS,
    MAINTAIN,
    GAIN
}


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private Double weight;
    private Double height;
    private String gender;
    @Enumerated(EnumType.STRING)
    private GoalType goal; 
    private Integer dailyCalorieGoal;
    private LocalDateTime createdAt;
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
