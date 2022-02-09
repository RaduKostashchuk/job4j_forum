package ru.job4j.forum.control;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegControl {
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public RegControl(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @PostMapping("/reg")
    public String doReg(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password) {
        String result = "redirect:/reg?error=true";
        if (!inMemoryUserDetailsManager.userExists(name)) {
            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(() -> "ROLE_USER");
            User user = new User(name, "{noop}" + password, roles);
            inMemoryUserDetailsManager.createUser(user);
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
