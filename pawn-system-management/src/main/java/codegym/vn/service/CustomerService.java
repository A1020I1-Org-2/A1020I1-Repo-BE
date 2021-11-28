package codegym.vn.service;


import codegym.vn.dto.CustomerDTO;
import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    void deleteCustomer(String customerId);

    Customer findById(String customerId);

    Page<Customer> searchCustomer(Date dateOfBirthForm, Date dateOfBirthTo, String address, String name, Pageable pageable);

    void save(CustomerDTO customerDTO);

    List<Customer> getCustomerList();
  
    Page<Customer> getCustomerList(Pageable pageable);

    Page<Customer> searchCustomer(String searchValue, Pageable pageable);

}
