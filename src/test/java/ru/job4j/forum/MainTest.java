package ru.job4j.forum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
    @AutoConfigureMockMvc
    public class MainTest {

        @Autowired
        MockMvc mockMvc;

        @Test
        public void testIndexController() throws Exception {
            this.mockMvc.perform(get("/index"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"));
        }

        @Test
        public void testLoginController() throws Exception {
            this.mockMvc.perform(get("/login"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("login"));
        }

        @Test
        public void testLoginControllerWithParameter() throws Exception {
            this.mockMvc.perform(get("/logout"))
                    .andDo(print())
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/login?logout=true"));
        }

        @Test
        public void testRegController() throws Exception {
            this.mockMvc.perform(get("/reg"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("reg"));
        }

        @Test
        public void testPostControllerShowPost() throws Exception {
            this.mockMvc.perform(get("/post?postId=1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("post"));
        }

        @Test
        @WithMockUser
        public void testPostControllerShowAddPostPage() throws Exception {
            this.mockMvc.perform(get("/post/add"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("addpost"));
        }

    @Test
    @WithMockUser
    public void testPostControllerShowEditPostPage() throws Exception {
        this.mockMvc.perform(get("/post/edit?postId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editpost"));
    }

    @Test
    @WithMockUser
    public void testCommentControllerShowAddCommentPage() throws Exception {
        this.mockMvc.perform(get("/comment/add?postId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addcomment"));
    }

    @Test
    @WithMockUser
    public void testCommentControllerShowEditCommentPage() throws Exception {
        this.mockMvc.perform(get("/comment/edit?postId=1&commentId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editcomment"));
    }
}