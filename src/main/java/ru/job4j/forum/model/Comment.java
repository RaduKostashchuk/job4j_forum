package ru.job4j.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    private String content;
    private String author;
    private LocalDateTime created;

    public static Comment of(String content, String author) {
        Comment comment = new Comment();
        comment.content = content;
        comment.author = author;
        comment.created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        return comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{"
                + "id=" + id
                + ", content='" + content + '\''
                + ", created=" + created
                + '}';
    }
}
