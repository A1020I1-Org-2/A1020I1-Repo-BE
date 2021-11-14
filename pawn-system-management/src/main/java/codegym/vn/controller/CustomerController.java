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

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

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

    @PostMapping("/update")
    public ResponseEntity<Void> update(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && customerDTO.getCustomerId() != null) {
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
