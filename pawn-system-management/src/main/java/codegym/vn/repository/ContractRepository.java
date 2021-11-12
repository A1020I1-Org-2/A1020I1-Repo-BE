package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface ContractRepository extends JpaRepository<Contract, String> {
//    @Query("select c\n " +
//            "from Contract c\n" +
//            "inner join Customer cus on cus.customerId = c.customer.customerId \n" +
//            "inner join TypeContract typ on typ.typeContractId = c.typeContract.typeContractId \n" +
//            "inner join StatusContract sta on sta.statusContractId = c.statusContract.statusContractId \n" +
//            "where (cus.name like %:customer% or :productName IS NULL) \n" +
//            "and (c.productName like %:statusContract% or :statusContract IS NULL) \n" +
//            "and (sta.name like %:typeContract% or :typeContract IS NULL) and (typ.name like %:typeContract% or :typeContract IS NULL) \n" +
//            "and (c.startDate between :startDateFrom and :endDateTo \n" +
//            "  or c.liquidationDate between :startDateFrom and :endDateTo)")
//    Page<Contract> searchContract(@Param("customer") String customer, @Param("productName") String productName,
//                                  @Param("statusContract") String statusContract, @Param("typeContract") String typeContract,
//                                  @Param("startDateFrom") Date startDateFrom, @Param("endDateTo") Date endDateTo, Pageable pageable);

    @Query("select c\n " +
            "from Contract c\n" +
            "inner join Customer cus on cus.customerId = c.customer.customerId \n" +
            "inner join TypeContract typ on typ.typeContractId = c.typeContract.typeContractId \n" +
            "inner join StatusContract sta on sta.statusContractId = c.statusContract.statusContractId \n" +
            "where (cus.name like %:customer% ) \n" +
            "and (c.productName like %:productName%) \n" +
            "and (sta.name like %:statusContract%) \n" +
            "and (typ.name like %:typeContract%) \n" +
            "and (c.startDate between :startDateFrom and :endDateTo) \n" +
            "or (c.liquidationDate between :startDateFrom and :endDateTo)")
    Page<Contract> searchContractTest(@Param("customer") String customer, @Param("productName") String productName,
                                      @Param("statusContract") String statusContract, @Param("typeContract") String typeContract,
                                      @Param("startDateFrom") Date startDateFrom, @Param("endDateTo") Date endDateTo,
                                      Pageable pageable);

}
