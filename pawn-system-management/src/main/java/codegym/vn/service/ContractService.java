package codegym.vn.service;

import codegym.vn.dto.ContractDto;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ContractService {
    Page<Contract> getLiquidationProductList(Pageable pageable);


    void saveLiquidationContract(ContractDto contractDto);

    Page<Contract> searchLiquidationProduct(
            String productName, String typeProduct, Integer receiveMoney,Pageable pageable);

    boolean updateStatusContractPawn(String contractID);

}
