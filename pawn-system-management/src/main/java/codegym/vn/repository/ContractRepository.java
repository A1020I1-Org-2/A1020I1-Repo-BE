package codegym.vn.repository;

import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

    @Query("select c\n " +
            "from Contract c\n" +
            "inner join Customer cus on cus.customerId = c.customer.customerId \n" +
            "inner join TypeContract typ on typ.typeContractId = c.typeContract.typeContractId \n" +
            "inner join StatusContract sta on sta.statusContractId = c.statusContract.statusContractId \n" +
            "where (cus.name like %:customer% ) \n" +
            "and (c.productName like %:productName%) \n" +
            "and (sta.name like %:statusContract%) \n" +
            "and (typ.name like %:typeContract%) \n" +
            "and ((c.startDate between :startDateFrom and :endDateTo) \n" +
            "or (c.liquidationDate between :startDateFrom and :endDateTo))")
    Page<Contract> searchContractTest(@Param("customer") String customer, @Param("productName") String productName,
                                      @Param("statusContract") String statusContract, @Param("typeContract") String typeContract,
                                      @Param("startDateFrom") Date startDateFrom, @Param("endDateTo") Date endDateTo,
                                      Pageable pageable);


    @Query( "select c\n" +
            "from Contract c\n" +
            "inner join Customer cus on cus.customerId = c.customer.customerId\n" +
            "inner join TypeContract t on t.typeContractId = c.typeContract.typeContractId\n" +
            "inner join StatusContract s on s.statusContractId = c.statusContract.statusContractId\n" +
            "where c.contractId like %:key% \n" +
            "or c.productName like %:key% or cus.name like %:key%  or t.name like %:key% or s.name like %:key%")
    List<Contract> searchListTop10(@Param("key") String name);

    List<Contract> findAllByStartDate(Date date);


    List<Contract> findTop10ByOrderByStartDateDesc();

    @Query(
            value="SELECT c FROM Contract c WHERE c.endDate = ?1"
    )
    List<Contract> findContractOutOfDate(Date CURDate);


    @Query(
           value = "select * from contract c inner join type_product  on" +
                   " c.type_product_id = type_product.type_product_id " +
                   "where (c.product_name like %:product_name% or c.product_name is null)" +
                   "and (c.receive_money like %:receive_money% or :receive_money is null )" +
                   "and (type_product.name like %:name% or type_product.name is null)"+
                   "and c.status_contract_id = 2",nativeQuery= true
    )
    Page<Contract> searchLiquidationProduct(@Param("product_name") String productName,
                                            @Param("receive_money") Integer receiveMoney,
                                            @Param("name") String typeProductName,
                                            Pageable pageable);
    @Query(
            value = "select ct from Contract ct inner join StatusContract sc on" +
                    " ct.statusContract.statusContractId = sc.statusContractId " +
                    "where sc.name = 'pending' "
    )
    Page<Contract> getLiquidationProductList(Pageable pageable);

}
