package codegym.vn.service;
import codegym.vn.entity.Contract;
import codegym.vn.entity.ContractDTO;
import codegym.vn.entity.Customer;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;

public interface ContractService {
    void createPawnContract(ContractDTO contract) throws MessagingException;

    Page<Customer> searchCustomer(String searchValue,Pageable pageable);


    Page<Contract> getLiquidationProductList(Pageable pageable);

    Page<Customer> getCustomerList(Pageable pageable);

    Page<Employee> getEmployeeList(Pageable pageable);

    void saveLiquidationContract(ContractDto contractDto);

    Page<Contract> searchLiquidationProduct(
            String productName, String typeProduct, Integer receiveMoney,Pageable pageable);
}
