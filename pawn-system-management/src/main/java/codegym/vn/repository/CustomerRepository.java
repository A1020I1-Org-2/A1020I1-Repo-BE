package codegym.vn.repository;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT cus FROM Customer cus \n" +
            "where (cus.dateOfBirth between :dateOfBirthFrom and :dataOfBirthTo) \n" +
            "and (cus.address like %:address%) \n" +
            "and (cus.name like %:name%)")
    Page<Customer> searchCustomer(@Param("dateOfBirthFrom") Date dateOfBirthFrom,
                                  @Param("dataOfBirthTo") Date dataOfBirthTo,
                                  @Param("address") String address,
                                  @Param("name") String name, Pageable pageable);

    @Query(value = "select cs from Customer cs where " +
                    "cs.customerId like %:searchValue% " +
                    "or cs.name like %:searchValue%  or " +
                    "cs.idCard like %:searchValue%"
    )
    Page<Customer> searchCustomer(@Param("searchValue") String searchValue, Pageable pageable);
}
