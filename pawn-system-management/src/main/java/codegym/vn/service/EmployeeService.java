package codegym.vn.service;

import codegym.vn.dto.EmployeeDto;
import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface EmployeeService {
    boolean createEmp (EmployeeDto employeeDto);
    Page<Employee> findAll(Pageable pageable);
    void deleteEmp(String id);
    Employee findById(String id);
    Page<Employee> searchByName(String key, Pageable pageable);
    boolean updateEmp(EmployeeDto employeeDto);
    void deleteAll();
    Page<Employee> getEmployeeList(Pageable pageable);
    Page<Employee> searchEmployee(String searchValue, Pageable pageable);

}
