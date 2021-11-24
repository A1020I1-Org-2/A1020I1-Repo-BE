package codegym.vn.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractController_edit {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEditContract_8() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/contract/edit/{id}","null"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testEditContract_9() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/contract/edit/{id}"," "))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void testEditContract_10() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/contract/edit/{id}","HD0001"))
                .andExpect(status().is2xxSuccessful());
    }
}
