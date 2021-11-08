package codegym.vn.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractRestController_findContractById {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDetailContract_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/contract/detail/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testDetailContract_2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/contract/detail/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testDetailContract_3() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/contract/detail/{id}", "HD-9999"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testDetailContract_4() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/contract/detail/{id}", "HD-0003"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.productName").value("Xe máy"))
                .andExpect(jsonPath("$.startDate").value("09-10-2021"))
                .andExpect(jsonPath("$.endDate").value("09-11-2021"));



    }
}
