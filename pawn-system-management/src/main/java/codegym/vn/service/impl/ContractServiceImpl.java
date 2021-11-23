package codegym.vn.service.impl;

import codegym.vn.dto.ContractDto;
import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.entity.Employee;
import codegym.vn.entity.StatusContract;
import codegym.vn.repository.ContractRepository;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.repository.EmployeeRepository;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Contract> getLiquidationProductList(Pageable pageable) {
        return contractRepository.getLiquidationProductList(pageable);
    }

    @Override
    public void saveLiquidationContract(ContractDto contractDto) {
        Customer customer = customerRepository.getById(contractDto.getCustomerId());
        Employee employee = employeeRepository.getById(contractDto.getEmployeeId());
        Contract contract = new Contract(
                contractDto.getContractId(),
                contractDto.getProductImg(),
                contractDto.getProductName(),
                contractDto.getInterestMoney(),
                contractDto.getReceiveMoney(),
                contractDto.getLoanMoney(),
                contractDto.getLiquidationDate(),
                contractDto.getStartDate(),
                contractDto.getEndDate(),
                contractDto.getQuantity(),
                contractDto.getStatusContract(),
                contractDto.getTypeProduct(),
                contractDto.getTypeContract(),
                employee,
                customer
        );
        contractRepository.save(contract);
    }

    @Override
    public Page<Contract> searchLiquidationProduct(
            String productName, String typeProduct, Integer receiveMoney, Pageable pageable) {
        return contractRepository.searchLiquidationProduct(productName, receiveMoney, typeProduct, pageable);
    }

    @Override
    public boolean updateStatusContractPawn(String contractID) {
        Contract contract = contractRepository.findById(contractID).orElse(null);
        if (contract == null) {
            return false;
        } else {
            contract.setStatusContract(new StatusContract(3, "Close"));
            contractRepository.save(contract);
            return true;
        }
    }
}
