package ru.job4j.forum.control;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class PostControlTest {

    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testPostControllerAddPost() throws Exception {
        this.mockMvc.perform(post("/post/add")
                        .param("name", "Новая тема"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument =
                ArgumentCaptor.forClass(Post.class);
        verify(posts).add(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("Новая тема"));
    }

    @Test
    @WithMockUser
    public void testPostControllerEditPostName() throws Exception {
        int postId = 1;
        when(posts.getById(postId)).thenReturn(Post.of("Тема", "user"));
        this.mockMvc.perform(post("/post/edit")
                        .param("postId", String.valueOf(postId))
                        .param("name", "Новая тема"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument =
                ArgumentCaptor.forClass(Post.class);
        verify(posts).add(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("Новая тема"));
    }

    @Test
    @WithMockUser
    public void testPostControllerDeletePost() throws Exception {
        int postId = 1;
        when(posts.getById(postId)).thenReturn(Post.of("Тема", "user"));
        this.mockMvc.perform(get("/post/delete?postId=" + postId))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Integer> argument =
                ArgumentCaptor.forClass(Integer.class);
        verify(posts).delete(argument.capture());
        MatcherAssert.assertThat(argument.getValue(), is(postId));
    }
}