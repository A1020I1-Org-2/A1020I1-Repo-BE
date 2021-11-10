package codegym.vn.controller;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.Contract;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

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
    public ResponseEntity<Contract> contractUpdate(@Validated @RequestBody EditContract editContract, BindingResult bindingResult){


        if (!bindingResult.hasFieldErrors() && editContract.getContractID() != null){
            this.contractService.contractUpdate(editContract);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contract> contractDelete(@PathVariable String id){
        Contract contract = this.contractService.findById(id);
        if (contract == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.contractService.contractDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
