package ru.job4j.forum.control;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class CommentControlTest {

    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testCommentControllerAddComment() throws Exception {
        int postId = 1;
        when(posts.getById(postId)).thenReturn(Post.of("Some post", "user"));
        this.mockMvc.perform(post("/comment/add")
                        .param("postId", String.valueOf(postId))
                        .param("content", "New comment"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument =
                ArgumentCaptor.forClass(Post.class);
        verify(posts).add(argument.capture());
        MatcherAssert.assertThat(argument.getValue()
                .getComments()
                .stream()
                .findFirst()
                .get()
                .getContent(), is("New comment"));
    }

    @Test
    @WithMockUser
    public void testCommentControllerEditComment() throws Exception {
        int postId = 1;
        int commentId = 1;
        Post post = Post.of("Some post", "user");
        Comment comment = Comment.of("Old comment", "user");
        comment.setId(commentId);
        post.addComment(comment);
        when(posts.getById(postId)).thenReturn(post);
        this.mockMvc.perform(post("/comment/edit")
                        .param("postId", String.valueOf(postId))
                        .param("commentId", String.valueOf(commentId))
                        .param("content", "New comment"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument =
                ArgumentCaptor.forClass(Post.class);
        verify(posts).add(argument.capture());
        MatcherAssert.assertThat(argument.getValue()
                .getComments()
                .stream()
                .findFirst()
                .get()
                .getContent(), is("New comment"));
    }

    @Test
    @WithMockUser
    public void testCommentControllerDeleteComment() throws Exception {
        int postId = 1;
        int commentId = 1;
        Post post = Post.of("Some post", "user");
        Comment comment = Comment.of("Old comment", "user");
        comment.setId(commentId);
        post.addComment(comment);
        when(posts.getById(postId)).thenReturn(post);
        this.mockMvc.perform(get("/comment/delete")
                        .param("postId", String.valueOf(postId))
                        .param("commentId", String.valueOf(commentId)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument1 =
                ArgumentCaptor.forClass(Post.class);
        verify(posts).add(argument1.capture());
        MatcherAssert.assertThat(argument1.getValue().getName(), is("Some post"));
    }
}