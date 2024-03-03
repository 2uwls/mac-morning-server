package morning.macmorning.repository.room;

import morning.macmorning.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaRoomRepository extends JpaRepository<Room,Long>, RoomRepository {


}
