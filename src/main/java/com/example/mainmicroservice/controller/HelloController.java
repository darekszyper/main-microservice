package com.example.mainmicroservice.controller;

import com.example.mainmicroservice.model.Message;
import com.example.mainmicroservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/main")
    public ResponseEntity<String> postMessage(@RequestParam String message) {
        Message newMessage = new Message();
        newMessage.setContent(message);
        messageRepository.save(newMessage);
        return new ResponseEntity<>("Message saved", HttpStatus.CREATED);
    }

    @GetMapping("/main")
    public ResponseEntity<String> getLastMessage() {
        return messageRepository.findTopByOrderByIdDesc()
                .map(message -> new ResponseEntity<>(message.getContent(), HttpStatus.OK))
                .orElse(new ResponseEntity<>("No messages found", HttpStatus.OK));
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
