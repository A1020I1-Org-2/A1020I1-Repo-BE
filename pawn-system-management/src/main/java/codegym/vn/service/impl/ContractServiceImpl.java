package codegym.vn.service.impl;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.*;
import codegym.vn.repository.*;
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
    @Autowired
    private StatusReponsitory statusReponsitory;
    @Autowired
    private TypeProductRepository typeProductRepository;
    @Autowired
    private TypeContractRepository typeContractRepository;


    @Override
    public Contract findById(String id) {
        return this.contractRepository.findById(id).orElse(null);
    }

    @Override
    public List<Contract> contractListTop10() {
        return this.contractRepository.findTop10ByOrderByStartDateDesc();
    }

    @Override
    public void contractUpdate(EditContract editContract) {
        Contract contract = this.contractRepository.getById(editContract.getContractID());

        Customer customer = this.customerRepository.getById(editContract.getCustomerID());

        StatusContract statusContract = this.statusReponsitory.getById(editContract.getStatusTypeID());

        TypeProduct typeProduct = this.typeProductRepository.getById(editContract.getProductTypeID());

        customer.setName(editContract.getCustomerName());
        contract.setCustomer(customer);
        contract.setTypeProduct(typeProduct);
        contract.setProductName(editContract.getProductName());
        contract.setStartDate(editContract.getStartDate());
        contract.setEndDate(editContract.getEndDate());
        contract.setStatusContract(statusContract);

        this.contractRepository.save(contract);

    }


    @Override
    public List<Contract> contractListTop10Search(String name) {
        System.out.println();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(name);
            Date date1 = (Date)date.clone();
            date.setHours(0);
            date1.setHours(23);
            return this.contractRepository.findAllByStartDate(date, date1);
        } catch (ParseException e) {
            return this.contractRepository.searchListTop10(name);
        }
    }


    @Override
    public void contractDelete(String id) {
        this.contractRepository.deleteById(id);
    }

    @Override
    public List<StatusContract> getAllStatus() {
        return this.statusReponsitory.findAll();
    }

    @Override
    public List<TypeContract> getAllTypeContract() {
        return this.typeContractRepository.findAll();
    }

    @Override
    public List<TypeProduct> getAllTypeProduct() {
        return this.typeProductRepository.findAll();
    }
}
