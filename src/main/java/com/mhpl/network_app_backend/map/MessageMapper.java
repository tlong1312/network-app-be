package com.mhpl.network_app_backend.map;

import com.mhpl.network_app_backend.dto.MessageDTO;
import com.mhpl.network_app_backend.entity.Message;

public class MessageMapper {
    public static MessageDTO toMessageDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setContent(message.getContent());
        dto.setCreatedAt(message.getCreatedAt());
        return dto;
    }
}
