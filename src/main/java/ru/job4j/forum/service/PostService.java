package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public Post add(Post post) {
        posts.save(post);
        return post;
    }

    public void delete(int id) {
        posts.findById(id).ifPresent(posts::delete);
    }

    public Post getById(int id) {
        return posts.findById(id).orElse(null);
    }

    public List<Post> getAll() {
        return (List<Post>) posts.findAll();
    }

}
