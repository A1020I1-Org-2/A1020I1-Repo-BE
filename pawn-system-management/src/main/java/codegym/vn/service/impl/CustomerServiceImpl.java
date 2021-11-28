package codegym.vn.service.impl;

import codegym.vn.dto.CustomerDTO;
import codegym.vn.entity.Customer;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }


    @Override
    public Page<Customer> searchCustomer(Date dateOfBirthForm, Date dateOfBirthTo, String address, String name, Pageable pageable) {
        return customerRepository.searchCustomer(dateOfBirthForm, dateOfBirthTo, address, name, pageable);
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setName(customerDTO.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday;
        try {
            birthday = format.parse(customerDTO.getDateOfBirth());
        } catch (ParseException e) {
            return;
        }
        customer.setDateOfBirth(birthday);
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setGender(customerDTO.isGender());
        customer.setIdCard(customerDTO.getIdCard());
        customer.setImg(customerDTO.getImg());
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getCustomerList(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> searchCustomer(String searchValue, Pageable pageable) {
        return customerRepository.searchCustomer(searchValue, pageable);
    }

}
