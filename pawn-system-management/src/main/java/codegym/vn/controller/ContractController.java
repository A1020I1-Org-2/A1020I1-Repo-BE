package codegym.vn.controller;

import codegym.vn.entity.Contract;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/returnList")
    public ResponseEntity<Page<Contract>> returnListContract(@PageableDefault(size = 4) Pageable pageable) {
        Page<Contract> contractList = contractService.findAllContract(pageable);
        if (contractList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractList, HttpStatus.OK);
    }

    @GetMapping("/searchContract")
    public ResponseEntity<Page<Contract>> searchContract(@RequestParam("contractId") String id,
                                                         @RequestParam("customerName") String customerName,
                                                         @RequestParam("product") String product,
                                                         @RequestParam("startDate") Date date,
                                                         @PageableDefault(size = 4) Pageable pageable) {
        Page<Contract> contracts = contractService.searchContract(id, customerName, product, date, pageable);
        if (contracts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/choose/{id}")
    public ResponseEntity<Contract> chooseContract(@PathVariable String id) {
        Contract contract = contractService.findById(id);
        if (contract == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(contract, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updateContract/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contract> updateContract(@Valid @RequestBody Contract contract, BindingResult bindingResult, @PathVariable String id) {
        if (!bindingResult.hasErrors() && id!= null) {
            if (contractService.findById(id) != null) {
                contractService.updateContract(contract, id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
