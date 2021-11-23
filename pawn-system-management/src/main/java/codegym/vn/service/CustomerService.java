package codegym.vn.service;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Page<Customer> getCustomerList(Pageable pageable);

    Page<Customer> searchCustomer(String searchValue, Pageable pageable);
}
