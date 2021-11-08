package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query("select c from Contract c" +
    " inner join Customer cus on cus.customerId = c.customer.customerId\n" +
    " inner join TypeContract typ on typ.typeContractId = c.typeContract.typeContractId\n" +
    " inner join StatusContract sta on sta.statusContractId = c.statusContract.statusContractId\n" +
    " where(cus.name like %?1%)" +
    " and (c.productName like %?2%)" +
    " and (sta.name like %?3%)" +
    " and (typ.name like %?4%)" +
    "and (c.startDate between ?5 and ?6) and (c.endDate between ?7 and ?8 )")
    Page<Contract> searchContract(String customer, String productName, String statusContract, String typeContract, String startDateFrom,
                                  String startDateTo, Pageable pageable);
}
