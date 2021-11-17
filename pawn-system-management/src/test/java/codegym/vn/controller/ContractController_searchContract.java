package codegym.vn.controller;

import codegym.vn.entity.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractController_searchContract {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContractController contractController;

    @Test
    public void searchContract_3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contract/listTop10/search","null"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void searchContract_4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contract/listTop10/search"," "))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void searchContract_5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contract/listTop10/search","HD000001"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void searchContract_6() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/listTop10/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void searchContract_7() {
        ResponseEntity<List<Contract>> responseEntity
                = this.contractController.contractSearch("HD0001");

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());


    }
}
