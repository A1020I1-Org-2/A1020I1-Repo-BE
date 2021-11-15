package codegym.vn.repository;

import codegym.vn.entity.Contract;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query( "select c\n" +
            "from Contract c\n" +
            "inner join Customer cus on cus.customerId = c.customer.customerId\n" +
            "inner join TypeContract t on t.typeContractId = c.typeContract.typeContractId\n" +
            "inner join StatusContract s on s.statusContractId = c.statusContract.statusContractId\n" +
            "where c.contractId like %:key% \n" +
            "or c.productName like %:key% or cus.name like %:key%  or t.name like %:key% or s.name like %:key%")
    List<Contract> searchListTop10(@Param("key") String name);

    List<Contract> findAllByStartDate(Date date);


    List<Contract> findTop10ByOrderByStartDateAsc();


}
