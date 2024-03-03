package morning.macmorning.repository.room;

import jakarta.persistence.EntityManager;
import morning.macmorning.domain.Room;

import javax.swing.text.html.Option;
import java.util.Optional;

public class JpaRoomRepository implements RoomRepository{
    private final EntityManager em;

    public JpaRoomRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Room save(Room room) {
        em.persist(room);
        return room;
    }

    @Override
    public Optional<Room> findById(Long id) {
        Room room = em.find(Room.class, id);
        return Optional.ofNullable(room);
    }
}
