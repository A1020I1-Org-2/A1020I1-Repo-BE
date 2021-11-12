package codegym.vn.repository;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
//    @Query(value = "SELECT cus.* FROM customer cus left join contract con \n" +
//            "on cus.customer_id = con.customer_id group by cus.customer_id;", nativeQuery = true)
    @Query(value = "SELECT * FROM customer", nativeQuery = true)
    Page<Customer> getListCustomer(Pageable pageable);


    @Query(value = "SELECT cus, count(con.contractId) as so_luong_hd FROM Customer cus left join Contract con \n" +
            "on cus.customerId = con.customer \n" +
            "where (cus.dateOfBirth between ?1 and ?2) \n" +
            "and (cus.address like %?3%) \n" +
            "and (cus.name like %?4%)\n" +
            "group by cus.customerId")
    Page<Customer> searchCustomer(String dateOfBirthFrom, String dataOfBirthTo, String address, String name, Pageable pageable);
}
