package morning.macmorning.repository.user;


import jakarta.persistence.EntityManager;
import morning.macmorning.domain.User;

import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository{

    private final EntityManager em;
    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }
    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> result = em.createQuery("select m from User m where m.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    public Optional<User> findByEmail(String email) {
        List<User> result = em.createQuery("select m from User m where m.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findAny();

    }
}
