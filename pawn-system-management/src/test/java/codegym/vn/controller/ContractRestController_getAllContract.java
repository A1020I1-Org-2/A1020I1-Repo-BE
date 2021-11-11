package codegym.vn.controller;

import codegym.vn.entity.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContractRestController_getAllContract {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContractController contractController;

    @Test
    public void testGetAllContract_5() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/listContract")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListContract_6() {

        ResponseEntity<Page<Contract>> responseEntity
                = this.contractController.getAllContract(PageRequest.of(0, 2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(3, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(5, responseEntity.getBody().getTotalElements());
    }

    @Test
    public void getListContract_7() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/search","null")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListContract_8() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/search","")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListContract_9() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/search","HD-0001")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListContract_10() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListContract_11() throws ParseException {
        ResponseEntity<Page<Contract>> responseEntity
                = this.contractController.searchContract("Le Hoa", "trang sức", "đã thanh lý",
                "cầm đồ", "2021-10-31", "2021-20-11", PageRequest.of(0, 2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(3, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(6, responseEntity.getBody().getTotalElements());
    }
}
