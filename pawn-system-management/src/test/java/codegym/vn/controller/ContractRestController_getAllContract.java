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
    public void getListContract_5() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/listContract")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListContract_6() {

        ResponseEntity<Page<Contract>> responseEntity
                = this.contractController.getAllContract(PageRequest.of(0, 2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("1",
                responseEntity.getBody().getContent().get(0).getContractId());
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
                        .get("/contract/search","123")
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
//    @Test
//    public void getListContract_11() {
//        ResponseEntity<Page<Contract>> responseEntity
//                = this.contractController.searchContract("Hoa","Xe máy",
//                "Đã cầm đồ","Cầm đồ","1999-01-01",
//                "2100-01-01",1,PageRequest.of(0,2));
//
//        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
//        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
//        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
//    }
}
