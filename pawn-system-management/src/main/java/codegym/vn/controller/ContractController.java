package codegym.vn.controller;

import codegym.vn.entity.Contract;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping("/listContract")
    public ResponseEntity<Page<Contract>> getAllContract(@PageableDefault(size = 5) Pageable pageable){
        Page<Contract> contractList = contractService.getContractList(pageable);
        if(contractList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractList, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Contract> findContractById(@PathVariable String id){
        Contract contract = contractService.findByContract(id);
        if(contract == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contract> deleteContract(@PathVariable String id){
        this.contractService.contractDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Contract>> searchContract(@RequestParam String customer,
                                                         @RequestParam String productName,
                                                         @RequestParam String statusContract,
                                                         @RequestParam String typeContract,
                                                         @RequestParam(defaultValue = "1999-01-01") String startDateFrom,
                                                         @RequestParam(defaultValue = "2100-01-01") String startDateTo,
                                                         @PageableDefault(size = 5) Pageable pageable){
        Page<Contract> contractList = contractService.searchContract(customer, productName, statusContract, typeContract, startDateFrom, startDateTo, pageable);
        if(contractList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
