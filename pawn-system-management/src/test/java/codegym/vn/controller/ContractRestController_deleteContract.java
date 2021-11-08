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
@SpringBootTest
@AutoConfigureMockMvc
public class ContractRestController_deleteContract {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDeleteContract_25() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/contract/delete/{id}","null"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testDeleteContract_26() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/contract/delete/{id}",""))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testDeleteContract_27() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/contract/delete/{id}","123"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteContract_28() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/contract/delete/{id}","2"))
                .andExpect(status().is2xxSuccessful());
    }
}
