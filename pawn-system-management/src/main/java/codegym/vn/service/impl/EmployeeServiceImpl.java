package codegym.vn.service.impl;
import codegym.vn.entity.Employee;
import codegym.vn.repository.EmployeeRepository;
import codegym.vn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> getEmployeeList(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
    @Override
    public Page<Employee> searchEmployee(String searchValue, Pageable pageable) {
        return employeeRepository.searchEmployee(searchValue,pageable);
    }

    // ThanhNHM
    @Override
    public List<Employee> getAllEmployee() {
        return this.employeeRepository.findAll();
    }
    // ThanhNHM
}
