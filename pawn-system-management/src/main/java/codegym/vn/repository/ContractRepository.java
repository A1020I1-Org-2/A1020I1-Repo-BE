package codegym.vn.repository;

import codegym.vn.entity.Contract;
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

    @Query( "select c\n" +
            "from Contract c\n" +
            " where c.startDate between :date and :date1")
    List<Contract> findAllByStartDate(@Param("date") Date date, @Param("date1") Date date1);



    List<Contract> findTop10ByOrderByStartDateDesc();

    @Query(
            value="SELECT c FROM Contract c WHERE c.endDate = ?1"
    )
    List<Contract> findContractOutOfDate(Date CURDate);


//    @Query(
//           value = "select * from contract c inner join type_product  on" +
//                   " c.type_product_id = type_product.type_product_id " +
//                   "where (c.product_name like %:product_name% or c.product_name is null)" +
//                   "and (c.loan_money like %:loan_money% or :loan_money is null )" +
//                   "and (type_product.name like %:name% or type_product.name is null)"+
//                   "and c.status_contract_id = 2",nativeQuery= true
//    )
    @Query("select c from Contract c inner join TypeProduct tp on " +
            "tp.typeProductId = c.typeProduct.typeProductId " +
            "where " +
            "(c.productName like %:product_name%) " +
            "and (concat(c.loanMoney, '')  like %:loan_money% ) " +
            "and (tp.name like %:name% ) " +
            "and c.statusContract.statusContractId = 2"
    )
    Page<Contract> searchLiquidationProduct(
            @Param("product_name") String productName,
            @Param("loan_money") String loanMoney,
            @Param("name") String typeProductName,
            Pageable pageable);

    @Query(value = "select ct from Contract ct inner join StatusContract sc on" +
                    " ct.statusContract.statusContractId = sc.statusContractId " +
                    "where sc.name = 'pending' "
    )
    Page<Contract> getLiquidationProductList(Pageable pageable);

    @Query("select c from Contract  c " +
            "join TypeProduct t on t.typeProductId= c.typeProduct.typeProductId " +
            "join StatusContract st on st.statusContractId = c.statusContract.statusContractId " +
            "where (c.productName like %:search%) and (t.name like %:typeSearch%) and (st.statusContractId <> 3)")
    Page<Contract> searchPawn(@Param("search") String search,
                              @Param("typeSearch") String typeSearch, Pageable pageable);

    @Query("select c from Contract c inner join StatusContract st " +
            "on st.statusContractId = c.statusContract.statusContractId where st.statusContractId <> 3")
    Page<Contract> getItemWarehouse(Pageable pageable);
}
