package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository comments;

    public CommentService(CommentRepository comments) {
        this.comments = comments;
    }

    public void delete(int id) {
        comments.findById(id).ifPresent(comments::delete);
    }
}
