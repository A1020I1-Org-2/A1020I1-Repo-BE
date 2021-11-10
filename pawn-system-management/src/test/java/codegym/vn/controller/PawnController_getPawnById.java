package codegym.vn.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PawnController_getPawnById {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPawnById_20() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pawn/pawnView", "null"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetPawnById_21() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pawn/pawnView", ""))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetPawnById_22() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pawn/pawnView", "123"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetPawnById_23() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pawn/pawnView", "1"))
                .andExpect(status().is2xxSuccessful());
    }
}
