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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping("/listContract")
    public ResponseEntity<Page<Contract>> getAllContract(@PageableDefault(size = 5) Pageable pageable){
        Page<Contract> contractList = this.contractService.getContractList(pageable);
        if(contractList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractList, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Contract> findContractById(@PathVariable String id){
        Contract contract = this.contractService.findById(id);
        if(contract == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contract> deleteContract(@PathVariable String id){
        Contract contract = contractService.findById(id);
        if (contract == null){
            return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
        }
        contractService.contractDelete(id);
        return new ResponseEntity<Contract>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Contract>> searchContract(@RequestParam("customer") String customer,
                                                         @RequestParam("productName") String productName,
                                                         @RequestParam("statusContract") String statusContract,
                                                         @RequestParam("typeContract") String typeContract,
                                                         @RequestParam("startDateFrom") String startDateFrom,
                                                         @RequestParam("endDateTo") String endDateTo,
                                                         @PageableDefault(size = 6) Pageable pageable) throws ParseException {
        Date searchStartDate;
        Date searchEndDate;
        if(startDateFrom.equals("")) {
            searchStartDate = new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01");
        }else {
            searchStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateFrom);
        }

        if(endDateTo.equals("")) {
            searchEndDate = new SimpleDateFormat("yyyy-MM-dd").parse("3000-01-01");
        }else {
            searchEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateTo);
        }

        Page<Contract> contractList = contractService.searchContract(customer,productName, statusContract, typeContract,
                searchStartDate, searchEndDate, pageable);
        if(contractList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractList, HttpStatus.OK);

    }

}
