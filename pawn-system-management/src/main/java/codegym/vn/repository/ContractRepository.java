package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ContractRepository extends JpaRepository<Contract, String> {

    @Query()
    Page<Contract> advancedSearchContract(String id, String customerName, String product, Date startDate, Pageable pageable);

}
