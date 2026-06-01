package ru.bgpu.autumn.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private Long id;
    private String text;
    private String userName;
    private Long roomId;
    private LocalDateTime timestamp;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String text, String userName, Long roomId, LocalDateTime timestamp) {
        this.id = id;
        this.text = text;
        this.userName = userName;
        this.roomId = roomId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
