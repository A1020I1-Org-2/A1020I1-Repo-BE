package codegym.vn.controller;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.Contract;
import codegym.vn.entity.StatusContract;
import codegym.vn.entity.TypeContract;
import codegym.vn.entity.TypeProduct;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/contract")
@CrossOrigin("http://localhost:4200")
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
    @GetMapping("/listTypeContract")
    public ResponseEntity<List<TypeContract>> listTypeContract(){
        List<TypeContract> typeContracts = this.contractService.getAllTypeContract();
        if (typeContracts == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeContracts,HttpStatus.OK);
    }
    @GetMapping("/listStatusContract")
    public ResponseEntity<List<StatusContract>> listStatusContract() {
        List<StatusContract> statusContracts = this.contractService.getAllStatus();
        if (statusContracts == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statusContracts, HttpStatus.OK);
    }
    @GetMapping("/listTypeProduct")
    public ResponseEntity<List<TypeProduct>> listTypeProduct() {
        List<TypeProduct> typeProducts = this.contractService.getAllTypeProduct();
        if (typeProducts == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeProducts, HttpStatus.OK);
    }
        @GetMapping("/listTop10/search")
    public ResponseEntity<List<Contract>> contractSearch(@RequestParam("key") String name){

        List<Contract> contracts = this.contractService.contractListTop10Search(name);
        if (contracts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Contract> contractUpdate(@Validated @RequestBody EditContract editContract, BindingResult bindingResult,@PathVariable String id){


        if (!bindingResult.hasFieldErrors() && editContract.getContractID() != null){
            this.contractService.contractUpdate(editContract);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/info/{id}")
    public ResponseEntity<Contract> contractDetail(@PathVariable String id){
        Contract contract = this.contractService.findById(id);
        if (contract==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contract,HttpStatus.OK);
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
