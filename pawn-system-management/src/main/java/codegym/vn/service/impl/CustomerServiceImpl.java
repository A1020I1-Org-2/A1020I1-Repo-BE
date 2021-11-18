package codegym.vn.service.impl;

import codegym.vn.entity.Customer;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getCustomerList(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> searchCustomer(String searchValue, Pageable pageable) {
        return customerRepository.searchCustomer(searchValue,pageable);
    }

}
