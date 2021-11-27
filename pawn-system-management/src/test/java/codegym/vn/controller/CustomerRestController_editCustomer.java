package codegym.vn.controller;

import codegym.vn.dto.CustomerDTO;
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
public class CustomerRestController_editCustomer {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void updateCustomer_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("null");
        customerDTO.setName("Rose");
        customerDTO.setDateOfBirth("1996/12/22");
        customerDTO.setEmail("abc@gmail.com");
        customerDTO.setAddress("New York");
        customerDTO.setPhone("0123456789");
        customerDTO.setGender(true);
        customerDTO.setIdCard("99999999999");
        customerDTO.setImg("http://abc");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/customer/update")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateCustomer_14() throws Exception {


        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("");
        customerDTO.setName("Rose");
        customerDTO.setDateOfBirth("1996/12/22");
        customerDTO.setEmail("abc@gmail.com");
        customerDTO.setAddress("New York");
        customerDTO.setPhone("0123456789");
        customerDTO.setGender(true);
        customerDTO.setIdCard("99999999999");
        customerDTO.setImg("http://abc");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/customer/update")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateCustomer_15() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("KH-0001");
        customerDTO.setName("Rose");
        customerDTO.setDateOfBirth("1996/12/22");
        customerDTO.setEmail("abmail.com");
        customerDTO.setAddress("New York");
        customerDTO.setPhone("0123456789");
        customerDTO.setGender(true);
        customerDTO.setIdCard("99999999999");
        customerDTO.setImg("http://abc");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/customer/update")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_16() throws Exception {


        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("KH-0001");
        customerDTO.setName("R");
        customerDTO.setDateOfBirth("1996/12/22");
        customerDTO.setEmail("abmail.com");
        customerDTO.setAddress("New York");
        customerDTO.setPhone("0123456789");
        customerDTO.setGender(true);
        customerDTO.setIdCard("99999999999");
        customerDTO.setImg("http://abc");


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/customer/update")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void updateCustomer_17() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("KH-0001");
        customerDTO.setName("Roseaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        customerDTO.setDateOfBirth("1996/12/22");
        customerDTO.setEmail("abmail.com");
        customerDTO.setAddress("New York");
        customerDTO.setPhone("0123456789");
        customerDTO.setGender(true);
        customerDTO.setIdCard("99999999999");
        customerDTO.setImg("http://abc");


        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/customer/update")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void updateEmployee_18() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("KH-0001");
        customerDTO.setName("Rose");
        customerDTO.setDateOfBirth("1996/12/22");
        customerDTO.setEmail("abc@gmail.com");
        customerDTO.setAddress("New York");
        customerDTO.setPhone("0123456789");
        customerDTO.setGender(true);
        customerDTO.setIdCard("99999999999");
        customerDTO.setImg("http://abc");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/customer/update")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


    }
}
