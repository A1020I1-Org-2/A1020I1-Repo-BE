package codegym.vn.service;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ContractService {
    Page<Contract> findAllContract(Pageable pageable);

    Contract findById(String id);

    Page<Contract> searchContract (String id, String customerName, String product, Date startDate, Pageable pageable);

    void updateContract(Contract contract, String id);
}
