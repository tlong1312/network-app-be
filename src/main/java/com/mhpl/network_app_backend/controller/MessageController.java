package com.mhpl.network_app_backend.controller;

import com.mhpl.network_app_backend.dto.MessageDTO;
import com.mhpl.network_app_backend.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestParam int senderId, @RequestParam int receiverId, @RequestParam String content) {
        messageService.sendMessage(senderId, receiverId, content);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDTO>> getConversation(@RequestParam int senderId, @RequestParam int receiverId) {
        return ResponseEntity.ok(messageService.getConversation(senderId, receiverId));
    }

    @GetMapping("/received")
    public ResponseEntity<List<MessageDTO>> getReceivedMessages(@RequestParam int receiverId) {
        return ResponseEntity.ok(messageService.getReceivedMessages(receiverId));
    }
}
