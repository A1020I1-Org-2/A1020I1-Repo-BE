package codegym.vn.repository;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(
            value = "select cs from Customer cs where (cs.customerId like %:customerId% or cs.customerId is null) " +
                    "and (cs.name like %:name% or cs.name is null) and (cs.idCard like %:idCard% or cs.idCard is null)"
    )
    Page<Customer> searchCustomer(@Param("customerId") String customerId,
                                  @Param("name") String name,
                                  @Param("idCard") String idCard, Pageable pageable);
}
