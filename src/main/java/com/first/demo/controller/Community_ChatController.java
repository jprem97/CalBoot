package com.first.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.first.demo.entity.ChatMessage;
import com.first.demo.service.ChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class Community_ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage message) {
        System.out.println(message);
        chatService.saveChats(message);
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

    
    @GetMapping("/getchats")
    
    public ResponseEntity<?> getMethodName(@RequestParam int page) {
         try {
            List<ChatMessage> chats=chatService.getChats(page);
            return new ResponseEntity<>(chats,HttpStatus.OK);
   
         } catch (Exception e) {
            return new ResponseEntity<>("no more chats",HttpStatus.BAD_REQUEST);
         }
         
    }
    
    
}
