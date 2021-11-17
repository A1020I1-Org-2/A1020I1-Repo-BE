package codegym.vn.controller;

import codegym.vn.entity.Customer;
import codegym.vn.entity.StatusContract;
import codegym.vn.service.CustomerService;
import codegym.vn.service.StatusContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/listCustomer")
    public ResponseEntity<List<Customer>> getCustomerList(){
        List<Customer> customerList = customerService.getCustomerList();
        if(customerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
}
