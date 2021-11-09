package codegym.vn.controller.contract;
        import codegym.vn.dto.ContractDto;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    private void createLiquidationContract_13() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("null");
        contractDto.setCustomerId("null");
        contractDto.setEmployeeId("null");


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
    private void createLiquidationContract_14() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("null");
        contractDto.setCustomerId("null");
        contractDto.setEmployeeId("null");

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
    private void createLiquidationContract_15() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("null");
        contractDto.setCustomerId("null");
        contractDto.setEmployeeId("null");

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
    private void createLiquidationContract_16() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("null");
        contractDto.setCustomerId("null");
        contractDto.setEmployeeId("null");

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
    private void createLiquidationContract_18() throws Exception {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId("null");
        contractDto.setCustomerId("null");
        contractDto.setEmployeeId("null");
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