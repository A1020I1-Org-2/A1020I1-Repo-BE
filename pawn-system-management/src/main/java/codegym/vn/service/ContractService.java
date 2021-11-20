package codegym.vn.service;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractService {
    Page<Contract> getAllPawn(Pageable pageable);

    Contract findPawnById(String id);

    Page<Contract> searchPawn(String search, String typeSearch, Pageable pageable);
}
