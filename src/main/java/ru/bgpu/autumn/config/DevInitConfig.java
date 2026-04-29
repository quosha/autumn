package ru.bgpu.autumn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bgpu.autumn.models.Message;
import ru.bgpu.autumn.models.Room;
import ru.bgpu.autumn.models.User;
import ru.bgpu.autumn.services.GroupService;
import ru.bgpu.autumn.services.MessageService;
import ru.bgpu.autumn.services.RoomService;
import ru.bgpu.autumn.services.UserService;

import java.util.Random;

@Component
@Profile("dev")
public class DevInitConfig implements CommandLineRunner {

    @Autowired UserService userService;
    @Autowired MessageService messageService;
    @Autowired RoomService roomService;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired GroupService groupService;

    @Override
    public void run(String... args) throws Exception {

        groupService.initGroup();

        Random random = new Random();

        Room room = roomService.save(new Room("Общая комината"));
        for(int i = 0; i < 10; i++) {
            User user = new User("test-"+i,"login-"+i);
            user.setPassword(passwordEncoder.encode("password"));
            user.getRooms().add(room);
            userService.save(user);
            for(int j = 0; j < 10; j++) {
                if (random.nextInt() < 10 * i) {
                    messageService.save(new Message(user, "msg-"+i+"-"+j));
                }
            }
        }
    }
}
