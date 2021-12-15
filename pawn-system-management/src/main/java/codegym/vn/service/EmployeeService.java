package codegym.vn.service;

import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Page<Employee> getEmployeeList(Pageable pageable);

    Page<Employee> searchEmployee(String searchValue, Pageable pageable);

    // ThanhNHM
    List<Employee> getAllEmployee();
    // ThanhNHM
}
