package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query(
            value="SELECT c FROM Contract c WHERE c.endDate = ?1"
    )
    List<Contract> findContractOutOfDate(Date CURDate);
}
