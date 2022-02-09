package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {
    private final List<Post> posts = new CopyOnWriteArrayList<>();
    private final AtomicInteger id = new AtomicInteger(1);

    @PostConstruct
    private void init() {
        Post post = Post.of("Продам санки", "admin");
        Comment comment1 = Comment.of("За сколько продаете?", "egor");
        Comment comment2 = Comment.of("500 рублей", "admin");
        post.addComment(comment1);
        post.addComment(comment2);
        this.add(post);
        this.add(Post.of("Погода на следующую неделю", "admin"));
        this.add(Post.of("Кто со мной в Шерегеш?", "admin"));
    }

    public Post add(Post post) {
        post.setId(id.incrementAndGet());
        posts.add(post);
        return post;
    }

    public void delete(int id) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == id) {
                posts.remove(i);
                return;
            }
        }
    }

    public Post getById(int id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Post> getAll() {
        return posts;
    }
}
