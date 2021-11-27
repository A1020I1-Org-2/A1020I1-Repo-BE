package codegym.vn.repository;

import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("select e from Employee e where e.fullName like %:key%")
    Page<Employee> searchByname(String key, Pageable pageable);
}
