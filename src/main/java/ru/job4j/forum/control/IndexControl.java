package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.service.PostService;

@Controller
public class IndexControl {
    private final PostService posts;

    public IndexControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "success", required = false) String success,
                        Model model) {
        String errorMessage = null;
        String successMessage = null;
        if (error != null) {
            errorMessage = "Post deletion failure";
        }
        if (success != null) {
            successMessage = "Post deleted successfully";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);
        model.addAttribute("posts", posts.getAll());
        return "index";
    }
}
