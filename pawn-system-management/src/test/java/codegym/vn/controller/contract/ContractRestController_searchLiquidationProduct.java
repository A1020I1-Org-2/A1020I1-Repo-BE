package codegym.vn.controller.contract;

import codegym.vn.controller.ContractController;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractRestController_searchLiquidationProduct {

    @Autowired
    private ContractController contractController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void searchLiquidationProduct_7() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/contract/search-liquidation-product", "null"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchLiquidationProduct_8() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/service/search/contract/search-liquidation-product", " "))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchLiquidationProduct_9() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/contract/search-liquidation-product", "Iphone 11"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchLiquidationProduct_10() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/contract/search-liquidation-product?product_name=&receive_money=&name=")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchLiquidationProduct_11() {
        ResponseEntity<Page<Contract>> responseEntity
                = this.contractController.searchLiquidationProduct("Iphone",0,"",
                PageRequest.of(0,2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(7, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(7, responseEntity.getBody().getTotalElements());
    }
}
