package codegym.vn.service.impl;

import codegym.vn.dto.CustomerDTO;
import codegym.vn.entity.Customer;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        if (customerRepository.findById(customerDTO.getCustomerId())!=null){
            customer.setCustomerId(customerDTO.getCustomerId());
        }else {
            return;
        }
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


}
