package com.mhpl.network_app_backend.service;

import com.mhpl.network_app_backend.dto.MessageDTO;
import com.mhpl.network_app_backend.entity.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(int senderId, int receiverId, String content);
    List<MessageDTO> getConversation(int senderId, int receiverId);
    List<MessageDTO> getReceivedMessages(int receiverId);
}
