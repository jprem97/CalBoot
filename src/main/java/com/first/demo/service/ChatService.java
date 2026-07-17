package com.first.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import com.first.demo.repo.mongo.ChatRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.first.demo.entity.*;
import com.first.demo.exception.ChatErrorException;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepo;

    
        public void saveChats(ChatMessage m)
        {
           ChatMessage s= chatRepo.save(m);
        //    if(s==null) throw new ChatErrorException("can't save the chat ");
        
        }
    
        public void deleteChats(String id ){
            ChatMessage m =chatRepo.removeById(id);
            if(m==null) throw new ChatErrorException("can't save the chat");
    
        }
        public List<ChatMessage> getChats(){
            Pageable pageable = PageRequest.of(0, 100,Sort.by("timestamp").descending());
            Page<ChatMessage> chats = chatRepo.findAll(pageable);
            if(chats.isEmpty()) throw new ChatErrorException("can't fetch the chats");
            return chats.getContent();
            
        }
    } 


