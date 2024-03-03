package morning.macmorning.controller;

import jakarta.servlet.http.HttpSession;
import morning.macmorning.domain.User;
import morning.macmorning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/signup")
    public String createSignupForm() {
        return "users/createUserForm";

    }
    @PostMapping(value = "/users/signup")
    public String signup(UserForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setName(form.getName());
        user.setPassword(form.getPassword());
        userService.join(user);


        return "redirect:/";
    }
    @GetMapping("/users/login")
    public String createLoginForm() {
        return "users/loginUserForm";
    }


    @PostMapping("/users/login")
    public String login(@ModelAttribute User user, HttpSession session) {
        Optional<User> loggedInUser = userService.login(user);

        if(loggedInUser.isPresent()) {
            User userToStoreInSession = loggedInUser.get();
            session.setAttribute("loggedInUser", userToStoreInSession);

            return "redirect:/";
        }
        return "users/loginUserForm";

    }
    @GetMapping("/user/profile")
    public String createUserProfile(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {

            model.addAttribute("user", loggedInUser);
            return "users/userProfile";
        } else {
            return "redirect:/users/login";
        }
    }

    @GetMapping("/user/profile/edit")
    public String editUserProfile(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if(loggedInUser != null) {
            model.addAttribute("email", loggedInUser.getEmail());
            model.addAttribute("password", loggedInUser.getPassword());
            model.addAttribute("name", loggedInUser.getName());

            return "users/editUserProfile";
        } else {
            return "redirect:/users/login";
        }
    }
    @PostMapping("user/profile/edit")
    public String editUserProfile(@ModelAttribute User updatedUser, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if(loggedInUser !=null) {
            loggedInUser.setEmail(updatedUser.getEmail());
            loggedInUser.setPassword(updatedUser.getPassword());
            loggedInUser.setName(updatedUser.getName());

            userService.updateUser(loggedInUser);

            return "redirect:/user/profile";
        } else {
            return "redirect:/users/login";
        }

    }



    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";


    }

}
