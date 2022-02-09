package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping("/post")
    public String showPostPage(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("post", posts.getById(Integer.parseInt(id)));
        return "post";
    }

    @GetMapping("/deletepost")
    public String delete(@RequestParam(value = "id") String id) {
        posts.delete(Integer.parseInt(id));
        return "redirect:/index";
    }

    @GetMapping("/addpost")
    public String showAddPostPage() {
        return "addpost";
    }

    @PostMapping("/addpost")
    public String add(@RequestParam(value = "name") String name) {
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        posts.add(Post.of(name, author));
        return "redirect:/index";
    }
}
