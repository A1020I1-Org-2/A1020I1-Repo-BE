package codegym.vn.repository;

import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("select e from Employee e where e.fullName like %:key%")
    Page<Employee> searchByname(String key, Pageable pageable);

    @Query(value = "select em from Employee em where " +
                    "em.employeeId like %:searchValue%  " +
                    "or em.fullName like %:searchValue% or " +
                    "em.idCard like %:searchValue% "
    )
    Page<Employee> searchEmployee(@Param("searchValue") String searchValue, Pageable pageable);

}
