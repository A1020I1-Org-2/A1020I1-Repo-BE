package codegym.vn.controller;

import codegym.vn.entity.Employee;
import codegym.vn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployeeList")
    public ResponseEntity<Page<Employee>> getEmployeeList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Employee> employeePage = this.employeeService.getEmployeeList(pageable);
        if (employeePage == null || employeePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Employee>>(employeePage,HttpStatus.OK);
    }

    @GetMapping(value = "/searchEmployee")
    public ResponseEntity<Page<Employee>> searchEmployee(@RequestParam(defaultValue = "") String searchValue,
                                                         @PageableDefault(value = 5) Pageable pageable){
        Page<Employee> employees = employeeService.searchEmployee(searchValue,pageable);
        if(employees == null){
            return new ResponseEntity<Page<Employee>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Employee>>(employees,HttpStatus.OK);
    }
}
