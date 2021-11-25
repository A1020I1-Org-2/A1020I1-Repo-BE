package codegym.vn;

import codegym.vn.dto.RegisterRequest;
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
public class HomeController_TestCreateRegister {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createRegister_13() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setRegisterPawnId(null);
        registerRequest.setName(null);
        registerRequest.setPhone("0794558312");
        registerRequest.setEmail("dokhoa34@gmail.com");
        registerRequest.setAddress("193 Nguyen Luong Bang");
        registerRequest.setPawTypeId(1);
        registerRequest.setNote("Can cam gap Lamborghini");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRegister_14() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setRegisterPawnId(null);
        registerRequest.setName("");
        registerRequest.setPhone("0794558312");
        registerRequest.setEmail("dokhoa34@gmail.com");
        registerRequest.setAddress("193 Nguyen Luong Bang");
        registerRequest.setPawTypeId(1);
        registerRequest.setNote("Can cam gap Lamborghini");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRegister_15() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setRegisterPawnId(null);
        registerRequest.setName("Do Khoa");
        registerRequest.setPhone("0794558312");
        registerRequest.setEmail("dokhoa34gmail.com");
        registerRequest.setAddress("193 Nguyen Luong Bang");
        registerRequest.setPawTypeId(1);
        registerRequest.setNote("Can cam gap Lamborghini");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRegister_16() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setRegisterPawnId(null);
        registerRequest.setName("Do Khoa");
        registerRequest.setPhone("079455");
        registerRequest.setEmail("dokhoa34@gmail.com");
        registerRequest.setAddress("193 Nguyen Luong Bang");
        registerRequest.setPawTypeId(1);
        registerRequest.setNote("Can cam gap Lamborghini");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRegister_17() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setRegisterPawnId(null);
        registerRequest.setName("Do Khoa");
        registerRequest.setPhone("079455831289879758658");
        registerRequest.setEmail("dokhoa34@gmail.com");
        registerRequest.setAddress("193 Nguyen Luong Bang");
        registerRequest.setPawTypeId(1);
        registerRequest.setNote("Can cam gap Lamborghini");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRegister_18() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setRegisterPawnId(null);
        registerRequest.setName("Do Khoa");
        registerRequest.setPhone("0794558312");
        registerRequest.setEmail("dokhoa34@gmail.com");
        registerRequest.setAddress("193 Nguyen Luong Bang");
        registerRequest.setPawTypeId(1);
        registerRequest.setNote("Can cam gap Lamborghini");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
