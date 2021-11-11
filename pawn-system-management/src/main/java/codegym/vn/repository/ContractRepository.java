package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query("select c\n " +
            "from Contract c\n" +
            "inner join Customer cus on cus.customerId = c.customer.customerId \n" +
            "inner join TypeContract typ on typ.typeContractId = c.typeContract.typeContractId\n" +
            "inner join StatusContract sta on sta.statusContractId = c.statusContract.statusContractId\n" +
            "where cus.name like %?1% or cus.name IS NULL \n" +
            "and c.productName like %?2% or c.productName IS NULL \n" +
            "and sta.name like %?3% or sta.name IS NULL and typ.name like %?4% or typ.name IS NULL \n" +
            "and c.startDate between ?5 and ?6 or c.startDate IS NULL\n" +
            "     or c.liquidationDate between ?5 and ?6 or c.liquidationDate IS NULL")
    Page<Contract> searchContract(String customer, String productName, String statusContract,
                                  String typeContract, Date startDateFrom,
                                  Date endDateTo, Pageable pageable);

}
