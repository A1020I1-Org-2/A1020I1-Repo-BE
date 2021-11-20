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
    public Page<Contract> getAllPawn(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public Contract findPawnById(String id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Contract> searchPawn(String search, String typeSearch, Pageable pageable) {
        return contractRepository.searchPawn(search,typeSearch,pageable);
    }

}
