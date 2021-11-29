package codegym.vn.controller;

import codegym.vn.entity.Customer;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import codegym.vn.dto.CustomerDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list-customer")
    public ResponseEntity<Page<Customer>> getListCustomer(@PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/getCustomerList")
    public ResponseEntity<Page<Customer>> getCustomerList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customerList = this.customerService.getCustomerList(pageable);
        if (customerList == null || customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity<List<Customer>> getCustomerList() {
        List<Customer> customerList = this.customerService.getCustomerList();
        if (customerList == null || customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        Customer customer = customerService.findById(customerId);
        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        if (customerService.findById(customerId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search-customer")
    public ResponseEntity<Page<Customer>> getSearchCustomer(@RequestParam("dateOfBirthFrom") String dateOfBirthFrom,
                                                            @RequestParam("dateOfBirthTo") String dateOfBirthTo,
                                                            @RequestParam("address") String address,
                                                            @RequestParam("name") String name,
                                                            @PageableDefault(size = 5) Pageable pageable) throws ParseException {
        System.out.println();
        Date searchDateFrom;
        Date searchDateTo;
        if (dateOfBirthFrom.equals("")) {
            searchDateFrom = new SimpleDateFormat("yyyy-MM-dd").parse("1900-1-1");
        } else {
            searchDateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthFrom);
        }

        if (dateOfBirthTo.equals("")) {
            searchDateTo = new SimpleDateFormat("yyyy-MM-dd").parse("2500-1-1");
        } else {
            searchDateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthTo);
        }

        Page<Customer> customers = customerService.searchCustomer(searchDateFrom, searchDateTo, address, name, pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/searchCustomer")
    public ResponseEntity<Page<Customer>> searchCustomer( @RequestParam(defaultValue = "") String searchValue,
                                                          @PageableDefault(value = 5) Pageable pageable){
        Page<Customer> customers = customerService.searchCustomer(searchValue,pageable);
        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers,HttpStatus.OK);
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
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
