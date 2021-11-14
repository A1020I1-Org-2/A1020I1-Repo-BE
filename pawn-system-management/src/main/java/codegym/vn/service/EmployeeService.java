package codegym.vn.service;

import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<Employee> searchEmployee(String employeeId, String fullName, String idCard, Pageable pageable);
}
