package codegym.vn.service;
import codegym.vn.dto.ContractDto;
import codegym.vn.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;

import codegym.vn.dto.EditContract;

import java.util.Date;
import java.util.List;

public interface ContractService {
    List<Contract> findAll();
    Page<Contract> getContractList(Pageable pageable);
    Page<Contract> searchContract(String customer, String productName, String statusContract, String typeContract, Date startDateFrom,
                                  Date endDateTo, Pageable pageable);


    Contract findById(String id);
    List<Contract> contractListTop10();
    void contractUpdate(EditContract editContract);
    List<Contract> contractListTop10Search(String name);
    void contractDelete(String id);
    List<StatusContract> getAllStatus();
    List<TypeContract> getAllTypeContract();
    List<TypeProduct> getAllTypeProduct();

    void createPawnContract(ContractDTO contract) throws MessagingException;

    Page<Customer> searchCustomer(String searchValue,Pageable pageable);
    Page<Contract> getLiquidationProductList(Pageable pageable);

    void saveLiquidationContract(ContractDto contractDto);

    Page<Contract> searchLiquidationProduct(String productName, String typeProduct, Integer receiveMoney, Pageable pageable);
    boolean updateStatusContractPawn(String contractID);

}
