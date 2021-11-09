package codegym.vn.service.impl;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.repository.ContractRepository;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Contract findById(String id) {
        return this.contractRepository.findById(id).orElse(null);
    }

    @Override
    public List<Contract> contractListTop10() {
        return this.contractRepository.findTop10ByOrderByStartDateAsc();
    }

    @Override
    public void contractUpdate(Contract contract) {
        contractRepository.save(contract);
    }


    @Override
    public List<Contract> contractListTop10Search(String name) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(name);
            return this.contractRepository.findAllByStartDate(date);
        } catch (ParseException e) {
            return this.contractRepository.searchListTop10(name);
        }
    }


    @Override
    public void contractDelete(Contract contract) {
        this.contractRepository.delete(contract);
    }
}
