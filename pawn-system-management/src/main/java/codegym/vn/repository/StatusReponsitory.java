package codegym.vn.repository;

import codegym.vn.entity.StatusContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusReponsitory extends JpaRepository<StatusContract, Integer> {

}
