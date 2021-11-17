package codegym.vn.service.impl;

import codegym.vn.entity.Customer;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }
}
