package codegym.vn.service.impl;

import codegym.vn.entity.Contract;
import codegym.vn.repository.ContractRepository;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Override
    public Page<Contract> getContractList(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public void contractDelete(String contractId) {
        contractRepository.deleteById(contractId);
    }

    @Override
    public Contract findByContract(String contractId) {
        return contractRepository.findById(contractId).orElse(null);
    }

    @Override
    public Page<Contract> searchContract(String customer, String productName, String statusContract, String typeContract,
                                         String startDateFrom, String startDateTo, Pageable pageable) {
        return contractRepository.searchContract(customer, productName, statusContract, typeContract, startDateFrom, startDateTo, pageable);
    }


}
