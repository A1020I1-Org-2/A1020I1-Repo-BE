package codegym.vn.controller;


import codegym.vn.dto.EmployeeDto;
import codegym.vn.dto.ListEmployeeResponse;
import codegym.vn.entity.Employee;
import codegym.vn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = {"/", "/list"})
    public ResponseEntity<ListEmployeeResponse> findAllEmp(@PageableDefault(size = 6) Pageable pageable) {
        Page<Employee> listEmp = this.employeeService.findAll(pageable);
        List<EmployeeDto> list = new ArrayList<>();
        for (Employee employee : listEmp.getContent()){
            list.add(new EmployeeDto(employee.getEmployeeId(), employee.getFullName(),
                    employee.getDateOfBirth().toString(), employee.getEmail(), employee.getAddress(),
                    employee.getPhone(), employee.isGender(), employee.getSalary(), employee.getIdCard(),
                    employee.getImg()));
        }
        if (listEmp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ListEmployeeResponse(list, listEmp.getTotalPages(), listEmp.getNumber()),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Employee> deleteEmp(@PathVariable String id) {
        Employee empId = this.employeeService.findById(id);
        if (empId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.employeeService.deleteEmp(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ListEmployeeResponse> searchByKey(@RequestParam String key,
                                                            @PageableDefault(value = 6) Pageable pageable){
        Page<Employee> listEmp = employeeService.searchByName(key, pageable);
        List<EmployeeDto> list = new ArrayList<>();
        for (Employee employee : listEmp.getContent()){
            list.add(new EmployeeDto(employee.getEmployeeId(), employee.getFullName(),
                    employee.getDateOfBirth().toString(), employee.getEmail(), employee.getAddress(),
                    employee.getPhone(), employee.isGender(), employee.getSalary(), employee.getIdCard(),
                    employee.getImg()));
        }
        if (listEmp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ListEmployeeResponse(list, listEmp.getTotalPages(), listEmp.getNumber()),
                HttpStatus.OK);
    }

    @PostMapping(value = "/create-employee")
    public ResponseEntity<List<FieldError>> createEmployee(@RequestBody EmployeeDto employee){
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getFieldErrors(),
//                    HttpStatus.NOT_ACCEPTABLE);
//        }
        if(!this.employeeService.createEmp(employee)){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/update-employee")
    public ResponseEntity<List<FieldError>> updateEmployee(@RequestBody EmployeeDto employee){
        System.out.println();
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getFieldErrors(),
//                    HttpStatus.NOT_ACCEPTABLE);
//        }
        if(!this.employeeService.createEmp(employee)){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping(value = "/view-employee/{id}")
    public ResponseEntity<EmployeeDto> detailEmployee(@PathVariable String id) {
        Employee employeeObj = this.employeeService.findById(id);
        if (employeeObj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new EmployeeDto(employeeObj.getEmployeeId(), employeeObj.getFullName(),
                employeeObj.getDateOfBirth().toString(), employeeObj.getEmail(), employeeObj.getAddress(),
                employeeObj.getPhone(), employeeObj.isGender(), employeeObj.getSalary(), employeeObj.getIdCard(),
                employeeObj.getImg()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<?> deleteAll() {
        this.employeeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getEmployeeList")
    public ResponseEntity<Page<Employee>> getEmployeeList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Employee> employeePage = this.employeeService.getEmployeeList(pageable);
        if (employeePage == null || employeePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeePage,HttpStatus.OK);
    }

    @GetMapping(value = "/searchEmployee")
    public ResponseEntity<Page<Employee>> searchEmployee(@RequestParam(defaultValue = "") String searchValue,
                                                         @PageableDefault(value = 5) Pageable pageable){
        Page<Employee> employees = employeeService.searchEmployee(searchValue,pageable);
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }
}
