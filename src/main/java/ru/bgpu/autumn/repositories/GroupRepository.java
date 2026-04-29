package ru.bgpu.autumn.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bgpu.autumn.models.Group;

import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Query("select g from Group g where g.name = ?1")
    Optional<Group> findOneByName(String name);

}
