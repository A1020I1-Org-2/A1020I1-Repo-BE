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

public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query(
            value="SELECT c FROM Contract c WHERE c.endDate = ?1"
    )
    List<Contract> findContractOutOfDate(Date CURDate);


    @Query(
           value = "select * from contract c inner join type_product  on" +
                   " c.type_product_id = type_product.type_product_id " +
                   "where (c.product_name like %:product_name% or c.product_name is null)" +
                   " and (c.receive_money = :receive_money or c.receive_money is null )" +
                   "and (type_product.name like %:name% or type_product.name is null)",nativeQuery= true
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
