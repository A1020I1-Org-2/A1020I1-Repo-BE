package codegym.vn.service.impl;

import codegym.vn.entity.Employee;
import codegym.vn.repository.EmployeeRepository;
import codegym.vn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee createEmp(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public void deleteGame(String id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Employee findById(String id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Employee> searchByName(String key, Pageable pageable) {
        return this.employeeRepository.searchByname(key, pageable);
    }

    @Override
    public Employee updateEmp(Employee employee) {
        return this.employeeRepository.save(employee);
    }
}
