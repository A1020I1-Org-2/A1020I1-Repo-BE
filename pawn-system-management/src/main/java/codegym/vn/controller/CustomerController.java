package codegym.vn.controller;

import codegym.vn.dto.CustomerDTO;
import codegym.vn.entity.Customer;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<CustomerDTO>> getLisCustomer() throws ParseException {
        List<Customer> customers= customerService.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer customer: customers){
            customerDTOList.add(new CustomerDTO(
                    customer.getCustomerId(),customer.getName(),customer.getDateOfBirth().toString(),customer.getEmail(),
                    customer.getAddress(),customer.getPhone(),customer.isGender(),customer.getIdCard(),customer.getImg()
            ));

        }
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createCustomer(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            customerService.save(customerDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        System.out.println();
        if (!bindingResult.hasErrors()) {
            if (customerService.findById(customerDTO.getCustomerId()) != null) {
                customerService.save(customerDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
