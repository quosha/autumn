package ru.bgpu.autumn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bgpu.autumn.models.Group;
import ru.bgpu.autumn.repositories.GroupRepository;
import ru.bgpu.autumn.util.AutumnDescription;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired private GroupRepository groupRepository;

    public void initGroup() {
        for(Field field : Group.class.getFields()) {
            if(field.isAnnotationPresent(AutumnDescription.class)) {
                try {
                    String name = field.get(null).toString();
                    Group group = getByName(name).orElse(save(new Group(name)));
                    group.setDescription(
                            field.getAnnotation(AutumnDescription.class).value()
                    );
                    save(group);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Optional<Group> getByName(String name) {
        return groupRepository.findOneByName(name);
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }
}
