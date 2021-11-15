package codegym.vn.controller;

import codegym.vn.dto.ContractDto;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.entity.Employee;
import codegym.vn.service.ContractService;
import codegym.vn.service.CustomerService;
import codegym.vn.service.EmployeeService;
import codegym.vn.service.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

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

    @GetMapping("/get-customer-list")
    public ResponseEntity<Page<Customer>> getCustomerList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customerList = this.contractService.getCustomerList(pageable);
        if (customerList == null || customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Customer>>(customerList,HttpStatus.OK);
    }

    @GetMapping("/get-employee-list")
    public ResponseEntity<Page<Employee>> getEmployeeList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Employee> employeePage = this.contractService.getEmployeeList(pageable);
        if (employeePage == null || employeePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Employee>>(employeePage,HttpStatus.OK);
    }

    @GetMapping("/search-liquidation-product")
    public ResponseEntity<Page<Contract>> searchLiquidationProduct(@RequestParam("product_name") String productName,
                                                                   @RequestParam("receive_money") Integer receiveMoney,
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
