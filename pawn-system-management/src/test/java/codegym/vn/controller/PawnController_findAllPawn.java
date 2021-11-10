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
public class PawnController_findAllPawn {
    @Autowired
    private PawnController pawnController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getListPawn_5() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/pawn/pawnList")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListPawn_6() {

        ResponseEntity<Page<Contract>> responseEntity
                = this.pawnController.getAllPawn(PageRequest.of(0, 2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("1",
                responseEntity.getBody().getContent().get(0).getContractId());
    }

    @Test
    public void getListPawn_7() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/pawn/pawnSearch","null")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListPawn_8() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/pawn/pawnSearch","")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListPawn_9() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/pawn/pawnSearch","123")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListPawn_10() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/pawn/pawnSearch")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListPawn_11() {
        ResponseEntity<Page<Contract>> responseEntity
                = this.pawnController.getAllPawnSearch("1","1",PageRequest.of(0,2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
    }

}
