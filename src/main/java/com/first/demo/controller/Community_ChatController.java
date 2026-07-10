package com.first.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.first.demo.entity.ChatMessage;
import com.first.demo.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class Community_ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<?> getChats() {
        try {
            List<ChatMessage> chats = chatService.getChats();
            return ResponseEntity.ok(chats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveChat(@RequestBody ChatMessage message) {
        try {
            chatService.saveChats(message);
            return ResponseEntity.ok("Chat saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable String id) {
        try {
            chatService.deleteChats(id);
            return ResponseEntity.ok("Chat deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
