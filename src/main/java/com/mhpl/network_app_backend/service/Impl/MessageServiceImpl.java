package com.mhpl.network_app_backend.service.Impl;

import com.mhpl.network_app_backend.dto.MessageDTO;
import com.mhpl.network_app_backend.entity.Message;
import com.mhpl.network_app_backend.entity.User;
import com.mhpl.network_app_backend.map.MessageMapper;
import com.mhpl.network_app_backend.repository.MessageRepository;
import com.mhpl.network_app_backend.repository.UserRepository;
import com.mhpl.network_app_backend.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void sendMessage(int senderId, int receiverId, String content) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người gửi!"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người nhận"));
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        messageRepository.save(message);
    }

    @Override
    public List<MessageDTO> getConversation(int senderId, int receiverId) {
        return messageRepository.findConversation(senderId, receiverId)
                .stream()
                .map(MessageMapper::toMessageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> getReceivedMessages(int receiverId) {
        return messageRepository.findByReceiverIdOrderByCreatedAtDesc(receiverId)
                .stream()
                .map(MessageMapper::toMessageDTO)
                .collect(Collectors.toList());
    }
}
