package morning.macmorning.service;

import morning.macmorning.domain.Room;
import morning.macmorning.domain.User;
import morning.macmorning.repository.room.RoomRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Long make(Room room) {


        roomRepository.save(room);
        System.out.println(room.getId());
        room.setInviteLink(String.valueOf(room.getId()));

        return room.getId();

    }

    public Optional<Room> findRoom(Long roomId) {
        return roomRepository.findById(roomId);
    }
}
