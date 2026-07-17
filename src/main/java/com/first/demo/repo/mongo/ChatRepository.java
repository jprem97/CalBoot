package com.first.demo.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
import com.first.demo.entity.ChatMessage;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {
    ChatMessage removeById(String id);
}
