package codegym.vn.service;

import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface EmployeeService {
    Employee createEmp (Employee employee);
    Page<Employee> findAll(Pageable pageable);
    void deleteGame(String id);
    Employee findById(String id);
    Page<Employee> searchByName(String key, Pageable pageable);
    Employee updateEmp(Employee employee);
}
