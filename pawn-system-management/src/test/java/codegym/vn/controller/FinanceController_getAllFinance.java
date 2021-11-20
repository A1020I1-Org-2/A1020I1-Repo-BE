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
public class FinanceController_getAllFinance {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllFinance_25() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/finance/financeView","null"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testGetAllFinance_26() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/finance/financeView",""))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testGetAllFinance_27() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/finance/financeView","123"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testGetAllFinance_28() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/finance/financeView","1"))
                .andExpect(status().is2xxSuccessful());
    }

}
