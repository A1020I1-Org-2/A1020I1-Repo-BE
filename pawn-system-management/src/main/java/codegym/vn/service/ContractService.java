package codegym.vn.service;

import codegym.vn.dto.ContractDto;
import codegym.vn.entity.Contract;
import codegym.vn.entity.ContractDTO;
import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;


public interface ContractService {
    Page<Contract> getLiquidationProductList(Pageable pageable);

    void saveLiquidationContract(ContractDto contractDto);

    Page<Contract> searchLiquidationProduct(
            String productName, String typeProduct, Integer receiveMoney, Pageable pageable);

    boolean updateStatusContractPawn(String contractID);
    void createPawnContract(ContractDTO contract) throws MessagingException;

    Page<Customer> searchCustomer(String searchValue,Pageable pageable);


}
