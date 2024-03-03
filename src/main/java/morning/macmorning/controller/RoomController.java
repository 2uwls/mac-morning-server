package morning.macmorning.controller;

import jakarta.servlet.http.HttpSession;
import morning.macmorning.domain.Room;
import morning.macmorning.domain.User;
import morning.macmorning.service.RoomService;
import morning.macmorning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RoomController {
    private final RoomService roomService;
    private final UserService userService;

    @Autowired
    public RoomController(RoomService roomService, UserService userService) {
        this.roomService = roomService;

        this.userService = userService;
    }

    @GetMapping(value = "/room/new")
    public String createRoomForm() {
        return "room/createRoom";
    }


    @PostMapping(value = "/room/new")
    public String createRoom(RoomForm form, HttpSession session) {
        Room room = new Room();
        room.setRoomName(form.getRoomName());
        room.setRoomDescription(form.getRoomDescription());
        room.setRoomDuration(form.getRoomDuration());

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if(loggedInUser !=null) {

            roomService.make(room);

            loggedInUser.changeRoom(room);
            loggedInUser.setRole("manager");

            userService.updateUser(loggedInUser);
            return "room/createInviteLink";
        } else {
            return "redirect:/users/login";
        }
    }



    @GetMapping(value = "/room/invite")
    public String createLink() {
        return "room/createInviteLink";
    }

    @GetMapping(value = "/room/join/{roomId}")
    public String accessRoom(@PathVariable(name="roomId") Long roomId, Model model) {
        Optional<Room> roomOptional = roomService.findRoom(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            model.addAttribute("room", room);
            System.out.println("Retrieved room: " + room);
            return "room/roomInvitation";
        }  else {
            return "roomNotFound";
        }
    }

    @PostMapping(value = "/room/join/{roomId}")
    public String joinRoom(@PathVariable(name = "roomId") Long roomId, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        Optional<Room> roomOptional = roomService.findRoom(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            loggedInUser.changeRoom(room);
            loggedInUser.setRole("member");
            userService.updateUser(loggedInUser);



            return "redirect:/room/roomInvitation/" + roomId;
        } else {
            return "roomNotFound";
        }
    }


}
