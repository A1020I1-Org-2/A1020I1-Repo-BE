package codegym.vn.service.impl;

import codegym.vn.entity.Contract;
import codegym.vn.repository.ContractRepository;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Page<Contract> findAllContract(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public Contract findById(String id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Contract> searchContract(String id, String customerName, String product, Date startDate, Pageable pageable) {
        return contractRepository.advancedSearchContract(id, customerName, product, startDate, pageable);
    }

    @Override
    public void updateContract(Contract contract, String id) {
        contractRepository.save(contract);
    }
}
