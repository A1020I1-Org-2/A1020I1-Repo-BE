package codegym.vn.service.impl;

import codegym.vn.entity.Contract;
import codegym.vn.repository.ContractRepository;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;


    @Override
    public Contract findById(String id) {
        return this.contractRepository.findById(id).orElse(null);
    }

    @Override
    public List<Contract> contractListTop10() {
        return this.contractRepository.findTop10ByOrderByStartDateAsc();
    }

    @Override
    public void contractEdit(Contract contract) {
        this.contractRepository.save(contract);
    }

    @Override
    public List<Contract> contractListTop10Search(String name) {
        return this.contractRepository.searchListTop10(name);
    }
}
