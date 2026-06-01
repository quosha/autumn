package ru.bgpu.autumn.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.bgpu.autumn.models.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findByNameContainingIgnoreCase(String name);
}
