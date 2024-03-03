package morning.macmorning.config;

import com.sun.source.tree.MemberSelectTree;
import jakarta.persistence.EntityManager;
import morning.macmorning.repository.room.RoomRepository;

import morning.macmorning.repository.user.MemoryUserRepository;
import morning.macmorning.repository.user.UserRepository;
import morning.macmorning.service.RoomService;
import morning.macmorning.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class WebConfig {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    public WebConfig(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }
    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }
    @Bean
    public RoomService roomService() {
        return new RoomService(roomRepository);
    }
}
