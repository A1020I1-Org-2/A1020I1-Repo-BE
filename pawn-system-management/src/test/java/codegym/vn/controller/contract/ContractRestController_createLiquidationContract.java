package codegym.vn.controller.contract;

import codegym.vn.dto.ContractDto;
import codegym.vn.entity.StatusContract;
import codegym.vn.entity.TypeContract;
import codegym.vn.entity.TypeProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractRestController_createLiquidationContract {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createLiquidationContract_13() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("null");
        contractDto.setCustomerId("null");
        contractDto.setEmployeeId("null");
        contractDto.setProductName("null");
        contractDto.setProductImg("null");
        contractDto.setQuantity(0);
        contractDto.setLoanMoney(0);
        contractDto.setInterestMoney(500);
        contractDto.setReceiveMoney(500);
        contractDto.setStartDate(null);
        contractDto.setEndDate(null);
        contractDto.setLiquidationDate(null);
        contractDto.setStatusContract(null);
        contractDto.setTypeContract(null);
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/contract/create-liquidation-contract")
                                .content(this.objectMapper.writeValueAsString(contractDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createLiquidationContract_14() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("");
        contractDto.setCustomerId("");
        contractDto.setEmployeeId("");
        contractDto.setProductName("");
        contractDto.setProductImg("");
        contractDto.setQuantity(0);
        contractDto.setLoanMoney(0);
        contractDto.setInterestMoney(500);
        contractDto.setReceiveMoney(500);
        contractDto.setStartDate(null);
        contractDto.setEndDate(null);
        contractDto.setLiquidationDate(null);
        contractDto.setStatusContract(null);
        contractDto.setTypeContract(null);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/contract/create-liquidation-contract")
                                .content(this.objectMapper.writeValueAsString(contractDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createLiquidationContract_15() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("");
        contractDto.setCustomerId("001");
        contractDto.setEmployeeId("");
        contractDto.setProductName("");
        contractDto.setProductImg("");
        contractDto.setQuantity(0);
        contractDto.setLoanMoney(0);
        contractDto.setInterestMoney(500);
        contractDto.setReceiveMoney(500);
        contractDto.setStartDate(null);
        contractDto.setEndDate(null);
        contractDto.setLiquidationDate(null);
        contractDto.setStatusContract(null);
        contractDto.setTypeContract(null);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/contract/create-liquidation-contract")
                                .content(this.objectMapper.writeValueAsString(contractDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createLiquidationContract_16() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("HD-01");
        contractDto.setCustomerId("");
        contractDto.setEmployeeId("");
        contractDto.setProductName("");
        contractDto.setProductImg("");
        contractDto.setQuantity(0);
        contractDto.setLoanMoney(0);
        contractDto.setInterestMoney(500);
        contractDto.setReceiveMoney(500);
        contractDto.setStartDate(null);
        contractDto.setEndDate(null);
        contractDto.setLiquidationDate(null);
        contractDto.setStatusContract(null);
        contractDto.setTypeContract(null);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/contract/create-liquidation-contract")
                                .content(this.objectMapper.writeValueAsString(contractDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createLiquidationContract_17() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("HD-0001");
        contractDto.setCustomerId("");
        contractDto.setEmployeeId("");
        contractDto.setProductName("");
        contractDto.setProductImg("");
        contractDto.setQuantity(0);
        contractDto.setLoanMoney(0);
        contractDto.setInterestMoney(50000000);
        contractDto.setReceiveMoney(500);
        contractDto.setStartDate(null);
        contractDto.setEndDate(null);
        contractDto.setLiquidationDate(null);
        contractDto.setStatusContract(null);
        contractDto.setTypeContract(null);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/contract/create-liquidation-contract")
                                .content(this.objectMapper.writeValueAsString(contractDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createLiquidationContract_18() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("HD-0001");
        contractDto.setCustomerId("KH-0003");
        contractDto.setEmployeeId("NV-0003");
        contractDto.setProductName("Iphone");
        contractDto.setProductImg("1");
        contractDto.setQuantity(1);
        contractDto.setLoanMoney(50000);
        contractDto.setInterestMoney(50000000);
        contractDto.setReceiveMoney(5000000);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse("31/09/2021");
        contractDto.setStartDate(date);

        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formatter.parse("31/09/2021");
        contractDto.setEndDate(date1);

        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = formatter.parse("31/09/2021");
        contractDto.setLiquidationDate(date2);

        contractDto.setStatusContract(new StatusContract(1, "open"));
        contractDto.setTypeContract(new TypeContract(1, "Thanh lý"));
        contractDto.setTypeProduct(new TypeProduct(1, "Điện thoại"));
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/contract/create-liquidation-contract")
                                .content(this.objectMapper.writeValueAsString(contractDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}