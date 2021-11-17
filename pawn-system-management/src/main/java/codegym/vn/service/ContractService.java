package codegym.vn.service;
import codegym.vn.entity.Contract;
import codegym.vn.entity.ContractDTO;
import codegym.vn.entity.Customer;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.StatusContract;
import codegym.vn.entity.TypeContract;
import codegym.vn.entity.TypeProduct;

import java.util.Date;
import java.util.List;

public interface ContractService {

    Contract findById(String id);
    List<Contract> contractListTop10();
    void contractUpdate(EditContract editContract);
    List<Contract> contractListTop10Search(String name);
    void contractDelete(String  id);
    List<StatusContract> getAllStatus();
    List<TypeContract> getAllTypeContract();
    List<TypeProduct> getAllTypeProduct();

    void createPawnContract(ContractDTO contract) throws MessagingException;

    Page<Customer> searchCustomer(String searchValue,Pageable pageable);


    Page<Contract> getLiquidationProductList(Pageable pageable);

    void saveLiquidationContract(ContractDto contractDto);

    Page<Contract> searchLiquidationProduct(
            String productName, String typeProduct, Integer receiveMoney,Pageable pageable);

}
