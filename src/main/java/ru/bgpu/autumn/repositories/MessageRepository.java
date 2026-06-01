package ru.bgpu.autumn.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bgpu.autumn.models.Message;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByRoomIdOrderByTimestampAsc(Long roomId);
}
