package codegym.vn.service.impl;

import codegym.vn.dto.EmployeeDto;
import codegym.vn.entity.Employee;
import codegym.vn.repository.EmployeeRepository;
import codegym.vn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean createEmp(EmployeeDto employee) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Employee employee1 = new Employee();
        try {
            employee1.setEmployeeId(employee.getEmployeeId());
            employee1.setFullName(employee.getFullName());
            employee1.setDateOfBirth(format.parse(employee.getDateOfBirth()));
            employee1.setEmail(employee.getEmail());
            employee1.setAddress(employee.getAddress());
            employee1.setPhone(employee.getPhone());
            employee1.setGender(employee.isGender());
            employee1.setSalary(employee.getSalary());
            employee1.setIdCard(employee.getIdCard());
            employee1.setImg(employee.getImg());
        } catch (ParseException e) {
            return false;
        }
        this.employeeRepository.save(employee1);
        return true;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public void deleteEmp(String id) {
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
    public boolean updateEmp(EmployeeDto employee) {
//        this.employeeRepository.save(employee);
        return false;
    }

    @Override
    public void deleteAll() {
        this.employeeRepository.deleteAll();
    }

    @Override
    public Page<Employee> getEmployeeList(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> searchEmployee(String searchValue, Pageable pageable) {
        return employeeRepository.searchEmployee(searchValue,pageable);
    }
}
