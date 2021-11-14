package codegym.vn.repository;

import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(
            value = "select em from Employee em where (em.employeeId like %:employeeId% or em.employeeId is null) " +
                    "and (em.fullName like %:fullName% or em.fullName is null) " +
                    "and (em.idCard like %:idCard% or em.idCard is null)"
    )
    Page<Employee> searchEmployee(@Param("employeeId") String employeeId,
                                  @Param("fullName") String fullName,
                                  @Param("idCard") String idCard, Pageable pageable);
}
