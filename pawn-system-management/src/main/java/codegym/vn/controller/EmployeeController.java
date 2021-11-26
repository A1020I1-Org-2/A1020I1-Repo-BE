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
    EmployeeService employeeService;

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
//        Page<EmployeeDto> list = listEmp.co
        if (listEmp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ListEmployeeResponse(list, listEmp.getTotalPages()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Employee> deleteEmp(@PathVariable String id) {
        Employee empId = this.employeeService.findById(id);
        if (empId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.employeeService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public Page<Employee> searchByKey(@RequestParam String key, @PageableDefault(value = 6) Pageable pageable){
        Page<Employee> listEmp = employeeService.searchByName(key, pageable);
        if (listEmp.isEmpty()){
            return this.employeeService.findAll(pageable);
        }
        return listEmp;
    }

    @PostMapping(value = "/createEmployee")
    public ResponseEntity<List<FieldError>> createEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(),
                    HttpStatus.NOT_ACCEPTABLE);
        }
        this.employeeService.createEmp(employee);
        System.out.println(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<List<FieldError>> updateEmployee(@PathVariable @Valid String id, @RequestBody Employee employee, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(),
                    HttpStatus.NOT_ACCEPTABLE);
        }
        this.employeeService.updateEmp(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping(value = "/viewEmployee/{id}")
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

}
