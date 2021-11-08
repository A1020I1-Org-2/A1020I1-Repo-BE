package codegym.vn.repository;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "select * from customer " , nativeQuery = true)
    Page<Customer> getListCustomer(Pageable pageable);
}
