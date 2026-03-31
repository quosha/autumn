package ru.bgpu.autumn.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bgpu.autumn.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
