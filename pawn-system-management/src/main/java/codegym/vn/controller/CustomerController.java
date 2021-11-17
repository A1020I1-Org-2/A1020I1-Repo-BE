package codegym.vn.controller;

import codegym.vn.entity.Customer;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/listCustomer")
    public ResponseEntity<Page<Customer>> getListCustomer(@PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable String customerId) {
        Optional<Customer> customer = customerService.findById(customerId);
        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
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
    public ResponseEntity<Page<Customer>> getSearchCustomer(@PageableDefault(size = 5) Pageable pageable,
                                                            @RequestParam String customerId,
                                                            @RequestParam Date dateOfBirthFrom,
                                                            @RequestParam Date dateOfBirthTo,
                                                            @RequestParam String address,
                                                            @RequestParam String name) {
        Page<Customer> customers = customerService.searchCustomer(customerId, dateOfBirthFrom,dateOfBirthTo, address, name, pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
