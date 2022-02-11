package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import static ru.job4j.forum.Util.checkUser;

import java.util.Set;

@Controller
public class CommentControl {
    private final PostService posts;
    private final CommentService comments;

    public CommentControl(PostService posts, CommentService comments) {
        this.posts = posts;
        this.comments = comments;
    }

    @GetMapping("/comment/add")
    public String show(@RequestParam(value = "postId") String postId, Model model) {
        model.addAttribute("post", posts.getById(Integer.parseInt(postId)));
        return "addcomment";
    }

    @PostMapping("/comment/add")
    public String add(@RequestParam(value = "postId") String postId,
                      @RequestParam(value = "content") String content) {
        Post post = posts.getById(Integer.parseInt(postId));
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        post.addComment(Comment.of(content, author));
        posts.add(post);
        return "redirect:/post?postId=" + postId;
    }

    @GetMapping("/comment/edit")
    public String showEditCommentPage(@RequestParam(value = "postId") String postId,
                                      @RequestParam(value = "commentId") String commentId,
                                      Model model) {
        Post post = posts.getById(Integer.parseInt(postId));
        model.addAttribute("post", post);
        model.addAttribute("commentId", commentId);
        return "editcomment";
    }

    @PostMapping("/comment/edit")
    public String editComment(@RequestParam(value = "postId") String postId,
                              @RequestParam(value = "commentId") String commentId,
                              @RequestParam(value = "content") String content) {
        String result = "redirect:/post/edit?error=true&postId=" + postId;
        String commentAuthor = getCommentAuthor(Integer.parseInt(postId), Integer.parseInt(commentId));
        if (checkUser(commentAuthor)) {
            Post post = posts.getById(Integer.parseInt(postId));
            post.getComments().stream()
                    .filter(c -> c.getId() == Integer.parseInt(commentId))
                    .findFirst()
                    .ifPresent(c -> c.setContent(content));
            posts.add(post);
            result = "redirect:/post/edit?success=true&postId=" + postId;
        }
        return result;
    }

    @GetMapping("/comment/delete")
    public String delete(@RequestParam(value = "postId") String postId,
                         @RequestParam(value = "commentId") String commentId) {
        String result = "redirect:/post/edit?error=true&postId=" + postId;
        String commentAuthor = getCommentAuthor(Integer.parseInt(postId), Integer.parseInt(commentId));
        if (checkUser(commentAuthor)) {
            Post post = posts.getById(Integer.parseInt(postId));
            post.removeComment(Integer.parseInt(commentId));
            posts.add(post);
            comments.delete(Integer.parseInt(commentId));
            result = "redirect:/post/edit?success=true&postId=" + postId;
        }
        return result;
    }

    private String getCommentAuthor(int postId, int commentId) {
        Post post = posts.getById(postId);
        Set<Comment> comments = post.getComments();
        Comment comment = comments.stream()
                .filter(c -> c.getId() == commentId)
                .findFirst()
                .orElse(null);
        return comment == null ? "" : comment.getAuthor();
    }
}
