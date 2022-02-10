package ru.job4j.forum.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String author;
    private LocalDateTime created;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<Comment> comments = new HashSet<>();

    public static Post of(String name, String author) {
        Post post = new Post();
        post.name = name;
        post.author = author;
        post.created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", created=" + created
                + '}';
    }
}
