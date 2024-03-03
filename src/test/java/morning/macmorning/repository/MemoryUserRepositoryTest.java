package morning.macmorning.repository;


import morning.macmorning.domain.User;
import morning.macmorning.repository.user.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        User user = new User();
        user.setName("jieun");

        //when
        repository.save(user);

        //then
        User result = repository.findById(user.getId()).get();

    }
    @Test
    public void findByName() {
        User user1 = new User();
        user1.setName("jieun");
        repository.save(user1);

        User user2 = new User();
        user2.setName("saeyeon");
        repository.save(user2);

        User result = repository.findByName("user1").get();

        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findAll() {
        //given
        User user1 = new User();
        user1.setName("jieun");
        repository.save(user1);

        User user2 = new User();
        user2.setName("saeyeon");
        repository.save(user2);

        //when
        List<User> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);


    }

}
