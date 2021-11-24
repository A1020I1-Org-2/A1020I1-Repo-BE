package codegym.vn.controller;

import codegym.vn.entity.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractController_listTop10 {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContractController contractController;

    @Test
    public void testGetListContract_1(){
        ResponseEntity<List<Contract>> responseEntity = this.contractController.contractListTop10();
        Assertions.assertEquals(404,responseEntity.getStatusCodeValue());
    }
    @Test
    public void testGetListContract_2(){
        ResponseEntity<List<Contract>> responseEntity = this.contractController.contractListTop10();
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
    }
}
