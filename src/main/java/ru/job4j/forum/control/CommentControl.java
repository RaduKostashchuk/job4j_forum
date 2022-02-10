package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class CommentControl {
    private final PostService posts;

    public CommentControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping("/addcomment")
    public String show(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("post", posts.getById(Integer.parseInt(id)));
        return "addcomment";
    }

    @PostMapping("/addcomment")
    public String add(@RequestParam(value = "postId") String id,
                      @RequestParam(value = "content") String content) {
        Post post = posts.getById(Integer.parseInt(id));
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        post.addComment(Comment.of(content, author));
        posts.add(post);
        return "redirect:/post?id=" + id;
    }
}
