package com.first.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@Document(collection = "chat_messages")
public class ChatMessage {

    @Id
    private String id;
 
    private String senderEmail ;

    private String message;

    @Builder.Default
    private boolean deleted = false;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}