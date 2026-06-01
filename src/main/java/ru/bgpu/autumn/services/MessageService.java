package ru.bgpu.autumn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bgpu.autumn.models.Message;
import ru.bgpu.autumn.repositories.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired private MessageRepository messageRepository;

    public Message save(Message message) {
         return messageRepository.save(message);
    }

    public List<Message> findByRoomId(Long roomId) {
        return messageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }
}
