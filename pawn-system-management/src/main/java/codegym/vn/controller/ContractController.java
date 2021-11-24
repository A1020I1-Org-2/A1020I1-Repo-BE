package codegym.vn.controller;

import codegym.vn.dto.ContractDto;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.entity.Employee;
import codegym.vn.entity.TypeProduct;
import codegym.vn.repository.TypeProductRepository;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private TypeProductRepository typeProductRepository;

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
                                                                   @RequestParam(value = "receive_money",
                                                                           defaultValue = "0") String receiveMoney,
                                                                   @RequestParam("name") String typeProductName,
                                                                   @PageableDefault(size = 5) Pageable pageable) {
        int temp = 0;
        try {
            temp = Integer.parseInt(receiveMoney);
        } catch (NumberFormatException e) {
            temp = 0;
        }
        Page<Contract> contractPage = this.contractService.searchLiquidationProduct(productName, typeProductName,
                temp == 0 ? null : temp, pageable);
        if (contractPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractPage, HttpStatus.OK);
    }


    @GetMapping("/update-status-contract")
    public ResponseEntity<Contract> updateStatusContractPawn(@RequestParam(value = "contractID") String contractID) {
        contractService.updateStatusContractPawn(contractID);
        if (contractService.updateStatusContractPawn(contractID)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/getListTypeProduct")
    public ResponseEntity<List<TypeProduct>> findAll() {
        List<TypeProduct> list = typeProductRepository.findAll();
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
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

}
