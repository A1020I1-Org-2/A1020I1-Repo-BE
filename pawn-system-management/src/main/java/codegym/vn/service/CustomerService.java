package codegym.vn.service;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getCustomerList();
  
    Page<Customer> getCustomerList(Pageable pageable);

    Page<Customer> searchCustomer(String searchValue, Pageable pageable);

}
