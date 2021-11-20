package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query("select c from Contract  c " +
            "join TypeProduct t on t.typeProductId= c.typeProduct.typeProductId " +
            "where (c.productName like %:search%) and (t.name like %:typeSearch%)")
    Page<Contract> searchPawn(@Param("search") String search,
                              @Param("typeSearch") String typeSearch, Pageable pageable);
}
