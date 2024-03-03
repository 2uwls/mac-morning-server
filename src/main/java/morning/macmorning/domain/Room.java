package morning.macmorning.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROOMNAME")
    private String roomName;
    @Column(name = "ROOMDESCRIPTION")
    private String roomDescription;
    @Column(name = "ROOMDURATION")
    private String roomDuration;

    @Column(name = "INVITELINK", insertable=false)
    private String inviteLink;


    @OneToMany(mappedBy = "room")
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }
}
