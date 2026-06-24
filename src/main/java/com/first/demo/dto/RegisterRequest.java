package com.first.demo.dto;

import com.first.demo.GoalType.GoalTypeEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private Integer age;

    private Double weight;

    private Double height;

    private String gender;

    @Enumerated(EnumType.STRING)
    private GoalTypeEnum goalType;

}
