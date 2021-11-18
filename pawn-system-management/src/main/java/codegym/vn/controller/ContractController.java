package codegym.vn.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import codegym.vn.dto.ContractDto;
import codegym.vn.entity.Contract;
import codegym.vn.service.ContractService;
import codegym.vn.service.CustomerService;
import codegym.vn.service.EmployeeService;
import codegym.vn.service.impl.ContractServiceImpl;
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
import org.springframework.validation.BindingResult;
import javax.validation.Valid;



@RestController
@RequestMapping(value = "/contract")
@CrossOrigin("http://localhost:4200")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/listContract")
    public ResponseEntity<Page<Contract>> getAllContract(@PageableDefault(size = 6) Pageable pageable){
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contractService.contractDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
            searchStartDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1900");
        }else {
            searchStartDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDateFrom);
        }

        if(endDateTo.equals("")) {
            searchEndDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-3000");
        }else {
            searchEndDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDateTo);
        }

        Page<Contract> contractList = contractService.searchContract(customer,productName, statusContract, typeContract,
                searchStartDate, searchEndDate, pageable);
        if(contractList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractList, HttpStatus.OK);

    }

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
    @PutMapping("/edit")
    public ResponseEntity<Contract> contractUpdate(@Validated @RequestBody EditContract editContract, BindingResult bindingResult){


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

    @PostMapping("/create-liquidation-contract")
    public ResponseEntity<Contract> createLiquidationContract(@Valid @RequestBody ContractDto contractDto,
                                                              BindingResult bindingResult) {
        System.out.println(contractDto);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }else {
            this.contractService.saveLiquidationContract(contractDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping("/get-liquidation-product-list")
    public ResponseEntity<Page<Contract>> getLiquidationContractList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Contract> contractList = this.contractService.getLiquidationProductList(pageable);
        if (contractList == null || contractList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Contract>>(contractList,HttpStatus.OK);
    }

    @GetMapping("/search-liquidation-product")
    public ResponseEntity<Page<Contract>> searchLiquidationProduct(@RequestParam("product_name") String productName,
                                                                   @RequestParam("receive_money") int receiveMoney,
                                                                   @RequestParam("name") String typeProductName,
                                                                   @PageableDefault(size = 5) Pageable pageable) {
        Page<Contract> contractPage = this.contractService.searchLiquidationProduct(productName, typeProductName,
                                                                                    receiveMoney, pageable);
        if (contractPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractPage,HttpStatus.OK);

    }

}
