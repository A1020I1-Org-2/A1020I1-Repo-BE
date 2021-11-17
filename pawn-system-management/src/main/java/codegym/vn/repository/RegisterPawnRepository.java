package codegym.vn.repository;

import codegym.vn.entity.RegisterPawn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterPawnRepository extends JpaRepository<RegisterPawn, Integer> {
}
