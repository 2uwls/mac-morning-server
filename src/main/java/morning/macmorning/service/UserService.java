package morning.macmorning.service;

import morning.macmorning.domain.User;
import morning.macmorning.repository.user.MemoryUserRepository;
import morning.macmorning.repository.user.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    /*회원가입*/
    /*public Long join(User user) {
        validateDupicateUser(user);
        userRepository.save(user);
        return user.getId();
    }*/
    /*회원가입*/
//    public User join(String email, String name, String password) {
//        User user = new User();
//        user.setEmail(email);
//        user.setName(name);
//        user.setPassword(passwordEncoder.encode(password));
//        userRepository.save(user);
//        return user;
//    }
    public Long join(User user) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        validateDupicateEmail(user);
//        validateDupicateUser(user);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public Optional<User> login(User user) {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        return findUser.filter(u -> u.getPassword().equals(user.getPassword()));
    }



    public void updateUser(User user) {
        userRepository.save(user);
    }


    private void validateDupicateEmail(User user) {
        userRepository.findByEmail(user.getEmail())
                        .ifPresent(u -> {
                            throw new IllegalStateException("이미 존재하는 이메일입니다.");
                        });
    }


    /* 전체회원조회 */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
