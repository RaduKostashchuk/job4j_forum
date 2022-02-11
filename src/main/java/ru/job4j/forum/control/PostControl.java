package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import static ru.job4j.forum.Util.checkUser;

@Controller
public class PostControl {
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping("/post")
    public String showPostPage(@RequestParam(value = "postId") String postId, Model model) {
        model.addAttribute("post", posts.getById(Integer.parseInt(postId)));
        return "post";
    }

    @GetMapping("/post/edit")
    public String showEditPostPage(@RequestParam(value = "postId") String postId,
                                   @RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "success", required = false) String success,
                                   Model model) {
        String errorMessage = null;
        String successMessage = null;
        if (error != null) {
            errorMessage = "Post modification failure";
        }
        if (success != null) {
            successMessage = "Post modified successfully";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);
        model.addAttribute("post", posts.getById(Integer.parseInt(postId)));
        return "editpost";
    }

    @PostMapping("/post/edit")
    public String editPost(@RequestParam(value = "postId") String postId,
                           @RequestParam(value = "name") String name) {
        String result = "redirect:/post/edit?error=true&postId=" + postId;
        String postAuthor = posts.getById(Integer.parseInt(postId)).getAuthor();
        if (checkUser(postAuthor)) {
            Post post = posts.getById(Integer.parseInt(postId));
            if (name.length() != 0) {
                post.setName(name);
                posts.add(post);
            }
            result = "redirect:/post/edit?success=true&postId=" + postId;
        }
        return result;
    }

    @GetMapping("/post/delete")
    public String delete(@RequestParam(value = "postId") String postId) {
        String result = "redirect:/index?error=true";
        String postAuthor = posts.getById(Integer.parseInt(postId)).getAuthor();
        if (checkUser(postAuthor)) {
            posts.delete(Integer.parseInt(postId));
            result = "redirect:/index?success=true";
        }
        return result;
    }

    @GetMapping("/post/add")
    public String showAddPostPage() {
        return "addpost";
    }

    @PostMapping("/post/add")
    public String add(@RequestParam(value = "name") String name) {
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        posts.add(Post.of(name, author));
        return "redirect:/index";
    }
}
