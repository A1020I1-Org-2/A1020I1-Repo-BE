package codegym.vn.service;

import codegym.vn.dto.CustomerDTO;
import codegym.vn.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<Customer> findAll();
    Customer findById(String id);
    void save(CustomerDTO customerDTO);
}
