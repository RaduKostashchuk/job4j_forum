package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {
    private final UserService users;
    private final PasswordEncoder encoder;

    public RegControl(UserService users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @PostMapping("/reg")
    public String doReg(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password) {
        String result = "redirect:/reg?error=true";
        if (users.findUserByName(name) == null) {
            users.addUser(User.of(name, encoder.encode(password), true, users.findAuthorityByName("ROLE_USER")));
            result = "redirect:/login?reg=true";
        }
        return result;
    }

    @GetMapping("/reg")
    public String showReg(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "User already exists";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }
}
