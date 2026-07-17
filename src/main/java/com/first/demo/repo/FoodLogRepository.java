package com.first.demo.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.demo.entity.FoodLog;

@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, String> {

    List<FoodLog> findByUserId(String userId);

    List<FoodLog> findByUserIdAndLoggedAtBetween(
            String userId,
            LocalDateTime start,
            LocalDateTime end
    );

    void deleteByLoggedAtBefore(LocalDateTime cutoff);
}