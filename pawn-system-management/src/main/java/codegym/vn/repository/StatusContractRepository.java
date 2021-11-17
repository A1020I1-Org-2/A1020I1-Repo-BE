package codegym.vn.repository;

import codegym.vn.entity.StatusContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusContractRepository extends JpaRepository<StatusContract, Integer> {
}
