package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query(
           value = "select * from contract c inner join type_product  on" +
                   " c.type_product_id = type_product.type_product_id " +
                   "where c.product_name like %:product_name% or c.product_name is null" +
                   " and c.receive_money like %:receive_money% or c.receive_money is null " +
                   "and type_product.name like %:name% or type_product.name is null",nativeQuery= true
    )
    Page<Contract> searchLiquidationProduct(@Param("product_name") String productName,
                                            @Param("receive_money") Integer receiveMoney,
                                            @Param("name") String typeProductName,
                                            Pageable pageable);
    @Query(
            value = "select * from contract ct inner join status_contract on\n" +
                    "                     ct.status_contract_id = status_contract.status_contract_id where\n" +
                    "                    status_contract.name = 'open'",nativeQuery= true
    )
    Page<Contract> getLiquidationProductList(Pageable pageable);
}
