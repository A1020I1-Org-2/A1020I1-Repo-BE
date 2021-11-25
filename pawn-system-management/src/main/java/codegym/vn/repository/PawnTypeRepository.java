package codegym.vn.repository;

import codegym.vn.entity.PawnType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PawnTypeRepository extends JpaRepository<PawnType, Integer> {
}
