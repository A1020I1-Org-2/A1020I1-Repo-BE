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

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getCustomerList")
    public ResponseEntity<Page<Customer>> getCustomerList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customerList = this.customerService.getCustomerList(pageable);
        if (customerList == null || customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Customer>>(customerList,HttpStatus.OK);
    }

    @GetMapping(value = "/searchCustomer")
    public ResponseEntity<Page<Customer>> searchCustomer( @RequestParam(defaultValue = "") String searchValue,
                                                          @PageableDefault(value = 5) Pageable pageable){
        Page<Customer> customers = customerService.searchCustomer(searchValue,pageable);
        if(customers == null){
            return new ResponseEntity<Page<Customer>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Customer>>(customers,HttpStatus.OK);
    }
}
