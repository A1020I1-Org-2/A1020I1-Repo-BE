package codegym.vn.controller;

import codegym.vn.entity.Contract;
import codegym.vn.entity.ContractDTO;
import codegym.vn.entity.Customer;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {

    @Autowired
    ContractService contractService;

    @PostMapping(value = "/createPawn")
    public ResponseEntity<List<Error>> createPawn(@RequestBody ContractDTO contractDTO){
        if (contractDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            contractService.createPawnContract(contractDTO);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/searchCustomer")
    public ResponseEntity<Page<Customer>> searchCustomer(@PageableDefault(value = 5) Pageable pageable,
                                                         @RequestParam(defaultValue = "") String searchValue){
        Page<Customer> customers = contractService.searchCustomer(searchValue,pageable);
        if(customers == null){
            return new ResponseEntity<Page<Customer>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Customer>>(customers,HttpStatus.OK);
    }
}
