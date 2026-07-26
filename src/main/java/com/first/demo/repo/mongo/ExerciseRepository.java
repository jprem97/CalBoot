package com.first.demo.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.first.demo.entity.ExerciseLog;

@Repository
public interface  ExerciseRepository extends MongoRepository<ExerciseLog,String>{

    
}
