package codegym.vn.service;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ContractService {
    Page<Contract> getContractList(Pageable pageable);
    void contractDelete(String contractId);
    Contract findById(String id);
    Page<Contract> searchContract(String customer, String productName, String statusContract, String typeContract, Date startDateFrom,
                                  Date endDateTo, Pageable pageable);
}
