package ru.bgpu.autumn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.bgpu.autumn.dto.MessageDTO;
import ru.bgpu.autumn.dto.RoomDTO;
import ru.bgpu.autumn.exceptions.ResourceNotFoundException;
import ru.bgpu.autumn.models.Message;
import ru.bgpu.autumn.models.Room;
import ru.bgpu.autumn.models.User;
import ru.bgpu.autumn.services.MessageService;
import ru.bgpu.autumn.services.RoomService;
import ru.bgpu.autumn.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired private RoomService roomService;
    @Autowired private MessageService messageService;
    @Autowired private UserService userService;
    @Autowired private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/my")
    List<RoomDTO> myRooms(Authentication authentication) {
        return userService
                .getByLogin(authentication.getName())
                .map(user -> user.getRooms().stream()
                        .map(r -> new RoomDTO(r.getId(), r.getName()))
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @GetMapping("/search")
    List<RoomDTO> searchRooms(@RequestParam String q) {
        return roomService.searchByName(q)
                .stream()
                .map(r -> new RoomDTO(r.getId(), r.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping
    RoomDTO createRoom(Authentication authentication, @RequestBody RoomDTO dto) {
        User user = userService.getByLogin(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        Room room = new Room(dto.getName());
        room.getUsers().add(user);
        room = roomService.save(room);

        user.getRooms().add(room);
        userService.save(user);

        return new RoomDTO(room.getId(), room.getName());
    }

    @PostMapping("/{id}/join")
    RoomDTO joinRoom(Authentication authentication, @PathVariable Long id) {
        User user = userService.getByLogin(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        Room room = roomService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Комната не найдена"));

        if (!room.getUsers().contains(user)) {
            user.getRooms().add(room);
            userService.save(user);
        }

        return new RoomDTO(room.getId(), room.getName());
    }

    @GetMapping("/{id}")
    RoomDTO getRoom(@PathVariable Long id) {
        Room room = roomService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Комната не найдена"));
        return new RoomDTO(room.getId(), room.getName());
    }

    @GetMapping("/{id}/messages")


    List<MessageDTO> messages(@PathVariable Long id) {
        return messageService.findByRoomId(id)
                .stream()
                .map(m -> new MessageDTO(
                        m.getId(),
                        m.getText(),
                        m.getUser().getName(),
                        m.getRoom().getId(),
                        m.getTimestamp()))
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/messages")
    MessageDTO createMessage(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody MessageDTO dto) {

        Room room = roomService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Комната не найдена"));
        User user = userService.getByLogin(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        Message message = new Message(user, dto.getText());
        message.setRoom(room);
        message = messageService.save(message);

        MessageDTO result = new MessageDTO(
                message.getId(),
                message.getText(),
                message.getUser().getName(),
                message.getRoom().getId(),
                message.getTimestamp());

        messagingTemplate.convertAndSend("/topic/rooms/" + id, result);

        return result;
    }
}
