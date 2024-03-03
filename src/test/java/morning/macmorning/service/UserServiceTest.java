package morning.macmorning.service;

import morning.macmorning.domain.User;
import morning.macmorning.repository.user.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }
    @AfterEach
    public void afterEach() {
        userRepository.clearStore();
    }
    @Test
    public void 회원가입() throws Exception {
        //Given
        User user = new User();
        user.setName("hello");

        //when
        Long saveId = userService.join(user);

        //Then
        User findUser = userRepository.findById(saveId).get();
        assertEquals(user.getName(), findUser.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        User user1 = new User();
        user1.setName("spring");

        User user2 = new User();
        user2.setName("spring");

        //when
        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }



}
