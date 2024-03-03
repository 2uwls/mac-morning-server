package morning.macmorning.repository.room;

import morning.macmorning.domain.Room;


import java.util.Optional;

public interface RoomRepository {
    Room save(Room room);

    Optional<Room> findById(Long id);


}
