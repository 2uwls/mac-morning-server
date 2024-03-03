package morning.macmorning.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RoomForm {
    @NotEmpty(message = "방 이름은 필수 항목입니다.")
    @Size(min = 2, max = 10)
    private String roomName;
    @NotEmpty(message = "방 소개는 필수 항목입니다.")
    private String roomDescription;

    @NotEmpty(message = "방 기간은 필수 항목입니다.")
    private String roomDuration;


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomDuration() {
        return roomDuration;
    }

    public void setRoomDuration(String roomDuration) {
        this.roomDuration = roomDuration;
    }
}
