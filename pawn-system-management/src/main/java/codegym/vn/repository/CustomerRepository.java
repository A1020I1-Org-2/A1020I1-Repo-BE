package codegym.vn.repository;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(
            value = "select cs from Customer cs where " +
                    "cs.customerId like %:searchValue% " +
                    "or cs.name like %:searchValue%  or " +
                    "cs.idCard like %:searchValue%"
    )
    Page<Customer> searchCustomer(@Param("searchValue") String searchValue, Pageable pageable);
}
