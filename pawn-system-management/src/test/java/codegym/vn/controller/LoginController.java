package codegym.vn.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/login?userName=admin&password=123")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testLogin_2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/login?userName=employee&password=12")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testLogin_3() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/login")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
