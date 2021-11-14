package codegym.vn.service;

import codegym.vn.entity.Contract;
import codegym.vn.entity.ContractDTO;
import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;

public interface ContractService {
    void createPawnContract(ContractDTO contract) throws MessagingException;

    Page<Customer> searchCustomer(String searchValue,Pageable pageable);
}
