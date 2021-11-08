package codegym.vn.service;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractService {
    Page<Contract> getContractList(Pageable pageable);
    void contractDelete(String contractId);
    Contract findByContract(String contractId);
    Page<Contract> searchContract(String customer, String productName, String statusContract, String typeContract, String startDateFrom,
                                  String startDateTo, Pageable pageable);
}
