package codegym.vn.controller;

import codegym.vn.entity.Customer;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/listCustomer")
    public ResponseEntity<Page<Customer>> getListCustomer(@PageableDefault(value = 10) Pageable pageable) {
        Page<Customer> customers = customerService.getListCustomer(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        if (!customerService.findById(customerId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchCustomer")
    public ResponseEntity<Page<Customer>> getSearchCustomer(@PageableDefault(value = 5) Pageable pageable,
                                                            @RequestParam(defaultValue = "1900-01-01") String dateOfBirthFrom,
                                                            @RequestParam(defaultValue = "2100-01-01") String dateOfBirthTo,
                                                            @RequestParam(defaultValue = "")String address,
                                                            @RequestParam(defaultValue = "")String name) {
        Page<Customer> customers = customerService.searchCustomer(dateOfBirthFrom,dateOfBirthTo, address, name, pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
