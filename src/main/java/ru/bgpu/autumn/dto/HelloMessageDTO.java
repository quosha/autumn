package ru.bgpu.autumn.dto;

public class HelloMessageDTO {

    private String name;
    private String message;

    public HelloMessageDTO() {
    }

    public HelloMessageDTO(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
