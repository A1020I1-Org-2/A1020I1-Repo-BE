package codegym.vn.controller;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.service.ContractService;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/listTop10")
    public ResponseEntity<List<Contract>> contractListTop10(){
        List<Contract> contracts = this.contractService.contractListTop10();
        if (contracts == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contracts,HttpStatus.OK);
    }
    @GetMapping("/listTop10/search")
    public ResponseEntity<List<Contract>> contractSearch(@RequestParam("key") String name){

        List<Contract> contracts = this.contractService.contractListTop10Search(name);
        if (contracts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<Contract> contractUpdate(@Validated @RequestBody EditContract editContract, BindingResult bindingResult,
                                                   @PathVariable String id){
        Contract contract1 = this.contractService.findById(id);


        if (!bindingResult.hasFieldErrors() && id != null){
            contract1.getCustomer().setName(editContract.getCustomerName());
            contract1.setProductName(editContract.getProductName());
            contract1.getTypeProduct().setName(editContract.getProductType());
            contract1.setStartDate(editContract.getStartDate());
            contract1.setEndDate(editContract.getEndDate());
            contract1.getTypeContract().setName(editContract.getContractType());
            contract1.getStatusContract().setName(editContract.getStatus());
            this.contractService.contractUpdate(contract1);

            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Contract> contractDelete(@PathVariable String id){
        Contract contract = this.contractService.findById(id);
        if (contract == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.contractService.contractDelete(contract);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
