package com.first.demo.entity;

import java.time.LocalDateTime;

import com.first.demo.GoalType.GoalTypeEnum;

import jakarta.persistence.Column;
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
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private Integer age;
    private Double weight;
    private Double height;
    private String gender;
    @Enumerated(EnumType.STRING)
    private GoalTypeEnum goalType;
    private Integer dailyCalorieGoal;
    private LocalDateTime createdAt;
    private String refreshToken;
    private String role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = "ROLE_USER";
    }
}