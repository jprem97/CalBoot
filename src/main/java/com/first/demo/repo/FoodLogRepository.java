package com.first.demo.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.demo.entity.FoodLog;
import com.first.demo.entity.User;

@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, String> {

    List<FoodLog> findByUser(User user);

    List<FoodLog> findByUserAndLoggedAtBetween(
            User user,
            LocalDateTime start,
            LocalDateTime end
    );

    void deleteByLoggedAtBefore(LocalDateTime cutoff);
}